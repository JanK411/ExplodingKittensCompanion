# Builds the web app and serves the static bundle with nginx's stock config.
#
# Web only, on purpose: -PwebOnlyBuild drops :androidApp from the build (see
# settings.gradle.kts) and the context carries no androidApp/ or iosApp/ sources.
# Nothing is needed on the host but Docker itself — the Gradle wrapper fetches its
# own toolchain inside the image, and no Android SDK or Xcode is ever involved.
#
# Deliberately plain — no BuildKit-only syntax — so it also builds with the classic
# builder on machines without the buildx plugin.

# ---------- build ----------
FROM eclipse-temurin:21-jdk AS build

# Which bundle to ship:
#   compatibility - wasm, with a JS fallback picked at runtime for browsers that
#                   can't run it. Mirrors the two dev-run tasks in one artifact.
#   wasmJs        - wasm only. Smallest and fastest, modern browsers only.
#   js            - JS only. Widest reach, larger and slower.
ARG WEB_TARGET=compatibility

# The Kotlin toolchain downloads its own Node to drive yarn and webpack, and that
# binary links against libatomic, which the JDK base image does not carry.
RUN apt-get update \
    && apt-get install -y --no-install-recommends libatomic1 \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /src

# Build scripts first, sources second: editing a card text then re-running the build
# reuses this layer, which is where Gradle itself and every plugin jar get fetched.
COPY gradlew gradle.properties settings.gradle.kts build.gradle.kts ./
COPY gradle/ gradle/
COPY shared/build.gradle.kts shared/
COPY webApp/build.gradle.kts webApp/
RUN ./gradlew --no-daemon -PwebOnlyBuild --dry-run :webApp:composeCompatibilityBrowserDistribution

COPY . .

# A cold build downloads a few hundred MB: dependencies, plus the Node and Binaryen
# toolchains the wasm pipeline pulls in. All of it stays in this discarded stage.
RUN set -eux; \
    case "$WEB_TARGET" in \
      compatibility) task=composeCompatibilityBrowserDistribution; dist=composeWebCompatibility ;; \
      wasmJs)        task=wasmJsBrowserDistribution;               dist=wasmJs ;; \
      js)            task=jsBrowserDistribution;                   dist=js ;; \
      *) echo "WEB_TARGET must be compatibility, wasmJs or js (got '$WEB_TARGET')" >&2; exit 1 ;; \
    esac; \
    ./gradlew --no-daemon -PwebOnlyBuild ":webApp:$task"; \
    mv "webApp/build/dist/$dist/productionExecutable" /dist

# Source maps are several MB of the bundle and only useful with the sources at hand,
# which a deployed image doesn't have.
RUN find /dist -name '*.map' -delete

# ---------- serve ----------
FROM nginx:stable-alpine AS runtime

COPY --from=build /dist /usr/share/nginx/html

EXPOSE 80

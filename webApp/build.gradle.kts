import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    js {
        browser()
        binaries.executable()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.executable()
    }

    sourceSets {
        commonMain.dependencies {
            implementation(project(":shared"))

            implementation(libs.compose.ui)
        }
    }
}

// Wrapper around `docker build` so the image is one command from a clean checkout,
// same as every other deliverable in this project. The build itself happens inside
// the image (see Dockerfile), not here — this task deliberately does not depend on
// composeCompatibilityBrowserDistribution, so `docker build` and `docker compose up`
// stay usable on their own without Gradle having run first.
val dockerImageName = providers.gradleProperty("dockerImageName").orElse("exploding-kittens-companion")
val dockerImageTag = providers.gradleProperty("dockerImageTag").orElse("latest")
val dockerWebTarget = providers.gradleProperty("dockerWebTarget").orElse("compatibility")

tasks.register<Exec>("dockerImage") {
    group = "docker"
    description = "Builds the Docker image serving the web app. " +
            "Override with -PdockerImageName=… -PdockerImageTag=… -PdockerWebTarget=compatibility|wasmJs|js"

    workingDir = rootDir
    commandLine(
        "docker", "build",
        "--build-arg", "WEB_TARGET=${dockerWebTarget.get()}",
        "--tag", "${dockerImageName.get()}:${dockerImageTag.get()}",
        "."
    )

    // Docker itself decides what is up to date; caching this task on top of that
    // would only hide a rebuild the user explicitly asked for.
    outputs.upToDateWhen { false }
}
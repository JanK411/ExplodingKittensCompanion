# Exploding Kittens Companion

**Someone at the table can't read the deck. This app fixes that.**

You're playing Exploding Kittens with a deck printed in Polish, and half the table doesn't
read Polish. Somebody is holding a card and everyone is guessing. This app tells them what
it does, in a language they actually read.

Two taps to set up — *which box is on the table?*, *which language do you read?* — and then
all nine card types sit on one screen, no scrolling and no searching. Each tile shows the
name as printed on the physical card, so you can match what's in your hand, with your own
language underneath. Tap one for a plain-language summary, the edge cases people actually
argue about, and the verbatim rulebook paragraph if the argument keeps going.

The phone gets handed around a lot during a game, so switching the reading language costs one
tap and never closes the card you had open.

**Languages:** English · Nederlands · Deutsch · Polski
**Runs on:** Android · iOS · the web

---

## Getting it running

You need a JDK (21 is what CI uses). Everything else the Gradle wrapper fetches itself.

```bash
./gradlew :webApp:wasmJsBrowserDevelopmentRun   # quickest look — opens in your browser
./gradlew :webApp:jsBrowserDevelopmentRun       # same thing, for older browsers
./gradlew :androidApp:assembleDebug             # APK in androidApp/build/outputs/apk/debug/
```

For iOS, open [`iosApp/`](./iosApp) in Xcode and hit run.

If you're in IntelliJ IDEA or Android Studio, the run widget in the toolbar has
configurations for all of these already.

## Tests

The test suite lives in `commonMain`'s sibling `commonTest`, so it runs unchanged on every
target. Pick whichever is convenient:

```bash
./gradlew :shared:testAndroidHostTest      # fastest, and what CI runs
./gradlew :shared:wasmJsTest               # needs Firefox installed (headless Karma)
./gradlew :shared:jsTest                   # same
./gradlew :shared:iosSimulatorArm64Test    # macOS only
```

## How the project is laid out

```
shared/      the entire app — Compose UI, card data, everything
androidApp/  a few lines that call App()
webApp/      a few lines that call App()
iosApp/      a few lines that call App()
docs/        the official rulebooks the card texts come from
```

All the interesting code is in `shared/src/commonMain`. The three app modules are entry
points and nothing more — there's no platform-specific UI anywhere in the project.

Inside `shared`:

- `data/` — `Card` (the nine types), `Language`, and `CardCatalog`, which maps a card and a
  language to its text. Card wording is transcribed from the official rulebooks in
  [`docs/rules-source/`](./docs/rules-source).
- `ui/` — the setup screen, the card grid, the detail sheet, and the theme.

The code is commented to explain *why* things are the way they are, so if a decision looks odd,
the reasoning is usually right there next to it. [`App.kt`](./shared/src/commonMain/kotlin/nl/jjt/explodingkittenscompanion/App.kt)
is a good place to start reading.

## Adding a language

This is the contribution the project most wants, and it's meant to be easy — no UI work, just
text and one small drawing. Five steps:

1. Drop the official rulebook PDF into [`docs/rules-source/`](./docs/rules-source).
2. Add an entry to the `Language` enum — a two-letter code, and the language's name written
   *in that language*.
3. Copy `data/cards/CardsEnglish.kt` to `Cards<YourLanguage>.kt`, translate the nine cards,
   and register it in `CardCatalog`.
4. Add a block to `UiStrings` for the handful of strings the app says on its own behalf,
   including the title printed on that edition's box.
5. Add a flag next to the existing `ic_flag_*.xml` drawables and point `Language.flag` at it.

`CardCatalogTest` checks that every card in every language has a name, a summary, details and
rulebook text, that no two cards in a language share a name, and that the app's own strings
are all filled in; the flag is checked by the compiler. Miss a step and the build tells you
which one, rather than the app quietly showing a blank tile.

## CI

Every push runs the tests and builds a debug APK, which you can download from the run's
artifacts in the Actions tab. Release signing is wired to CI secrets; locally you'll get the
standard debug keystore without configuring anything.

---

Exploding Kittens is a game by Exploding Kittens LLC. This is an unofficial fan-made
companion — it doesn't reproduce the game, and you'll need a real deck to play. The rulebooks
in `docs/` are included as the reference the card texts were transcribed from.

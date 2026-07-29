# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## What this app is

A companion for the Exploding Kittens base game (56 cards, 9 distinct types). It exists for
one situation: someone is holding a deck printed in a language they don't read. They pick the
deck's language and their own, then tap a card to see what it does. That framing drives most
of the design decisions below — read the KDoc on `App.kt`, `Card.kt`, and `SetupScreen.kt`
before changing UI or data structures; the "why" is documented there rather than here.

## Commands

```bash
./gradlew :shared:testAndroidHostTest        # the test suite CI runs
./gradlew :shared:wasmJsTest                 # same commonTest, wasm target (headless Firefox)
./gradlew :shared:jsTest                     # same commonTest, JS target (headless Firefox)
./gradlew :shared:iosSimulatorArm64Test      # macOS only

./gradlew :androidApp:assembleDebug          # APK -> androidApp/build/outputs/apk/debug/
./gradlew :webApp:wasmJsBrowserDevelopmentRun   # dev server, modern browsers
./gradlew :webApp:jsBrowserDevelopmentRun       # dev server, older browsers
```

iOS: open `iosApp/` in Xcode and run from there.

Single test: `./gradlew :shared:testAndroidHostTest --tests "*CardCatalogTest.everyCardIsTranslatedIntoEveryLanguage"`.

Web tests run under Karma with **headless Firefox**, so they need Firefox on the machine.
There is no lint/format tooling configured; `kotlin.code.style=official` is the only setting.

## Module layout

- `shared/` — the whole app. All Compose UI, all data, all logic lives in `commonMain`.
  Targets Android, iosArm64, iosSimulatorArm64, js, wasmJs.
- `androidApp/`, `webApp/`, `iosApp/` — entry points only, a few lines each, all calling
  `App()`. Do not put features here.

There is no `iosMain`/`androidMain` business logic and no `expect`/`actual` in the project.
If a change seems to need platform code, that is a signal worth questioning first.

## Data model

`Card` (enum, 9 types) × `Language` (enum) → `CardText`, resolved through `CardCatalog`.
Card texts are transcribed from the official rulebooks in `docs/rules-source/*.pdf`, which are
the source of truth and are deliberately **not** bundled into the app (~14 MB).

`CardText` has four levels: `name` (as printed on the physical card), `summary`, `details`,
and verbatim `rulebook` text behind a "read more" toggle.

`Card` declaration order is the grid order. `Card.countInDeck` reflects physical card counts;
the five cat cards collapse into `CAT_CARD`.

### Adding a language

Three edits, all enforced by `CardCatalogTest` (an incomplete translation fails the build
rather than rendering a blank tile):

1. Add an entry to `Language` (`code` = two-letter switcher label, `nativeName` written in
   that language itself).
2. Add `data/cards/Cards<Language>.kt` and register it in `CardCatalog.byLanguage`.
3. Add a block to `UiStrings.byLanguage`.

Also drop the rulebook PDF into `docs/rules-source/`.

## Language handling

Two independent language dimensions, both explicit in-app state:

- **game language** — what the deck on the table is printed in; picked once in setup.
- **understood** — what the player reads; switchable in one tap from both the top bar and
  the detail sheet, because the phone gets handed around mid-game.

App strings live in `UiStrings`, **not** in `composeResources` string resources. That is
deliberate: resource strings follow the device locale, but the language here is an explicit
choice that changes when the phone changes hands. Keep new UI text in `UiStrings`.

## State and navigation

`App.kt` holds two `remember`d nullable `Language` values and nothing else — no ViewModel, no
nav library, no persistence. Setup costs two taps, which is cheaper than a storage
abstraction across three platforms. The `AnimatedContent` in `App.kt` keys on *which screen*
rather than on the language values, so switching the understood language does not tear down
the grid or close an open sheet.

## Theming

`ui/Theme.kt` defines the `Ek` palette and Material3 overrides; light-only on purpose. Card
accent colours in `ui/CardStyle.kt` mirror the icon colours the rulebooks print, so screen and
paper agree — keep that mapping intact when touching colours or icons.

Icons are vector drawables in `shared/src/commonMain/composeResources/drawable/`, accessed via
the generated `explodingkittenscompanion.shared.generated.resources.Res`.

## Conventions

Comments in this codebase explain *why* a decision was made, not what the code does, and
carry the product reasoning. Match that when adding code; don't strip those KDoc blocks during
refactors.

## CI

`.github/workflows/build.yml` runs on every push: `:shared:testAndroidHostTest`, then
`:androidApp:assembleDebug`, uploading a timestamped APK artifact. Signing is driven by the
`KEYSTORE_FILE` env var — set only on CI from repo secrets; without it, local and CI builds
fall back to the auto-generated debug keystore.

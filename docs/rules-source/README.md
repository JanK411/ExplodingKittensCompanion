# Rules source

Official Exploding Kittens rulebooks (base game, 56 cards) for the languages the app
supports. These are the **source** for the card texts in
`shared/src/commonMain/kotlin/nl/jjt/explodingkittenscompanion/data/cards/`.

They deliberately live here and not in `composeResources`, because the app does not read
them at runtime — bundling them would add ~14 MB to the APK and to the browser download.

When adding a language, drop its rulebook here and transcribe the card texts into a new
`Cards<Language>.kt`.

© Exploding Kittens LLC. Included for reference only.

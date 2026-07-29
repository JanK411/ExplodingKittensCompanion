package nl.jjt.explodingkittenscompanion.data

/**
 * A language the app can present cards in, and that a physical deck can be printed in.
 *
 * [nativeName] is always written in the language itself, so the setup screen can be
 * understood before we know which language the user speaks. [code] is the short label used
 * by the in-place language switcher.
 *
 * To add a language: add an entry here, add a `Cards<Language>.kt` next to the others and
 * register it in [CardCatalog], then add a block to [UiStrings]. `CardCatalogTest` fails
 * until all three are done.
 */
enum class Language(val code: String, val nativeName: String) {
    ENGLISH("EN", "English"),
    DUTCH("NL", "Nederlands"),
    GERMAN("DE", "Deutsch"),
    POLISH("PL", "Polski"),
}

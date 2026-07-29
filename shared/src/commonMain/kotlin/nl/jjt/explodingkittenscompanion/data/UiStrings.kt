package nl.jjt.explodingkittenscompanion.data

/**
 * The handful of strings the app says on its own behalf, in each [Language].
 *
 * Deliberately not `composeResources` string resources: those follow the *device* locale,
 * but the language here is an explicit in-app choice that changes whenever the phone is
 * handed to another player.
 */
data class UiStrings(
    /** Cycled through all languages on the first setup step, before we know who is holding the phone. */
    val iUnderstand: String,
    val gameLanguageQuestion: String,
    val gameLabel: String,
    /** Rendered after the count, e.g. "4 in the deck". */
    val inDeck: String,
    val readMore: String,
    val showLess: String,
    val fromTheRulebook: String,
) {
    companion object {
        private val byLanguage = mapOf(
            Language.ENGLISH to UiStrings(
                iUnderstand = "I understand English",
                gameLanguageQuestion = "Which language is the game?",
                gameLabel = "Game",
                inDeck = "in the deck",
                readMore = "Read more",
                showLess = "Show less",
                fromTheRulebook = "From the rulebook",
            ),
            Language.DUTCH to UiStrings(
                iUnderstand = "Ik versta Nederlands",
                gameLanguageQuestion = "In welke taal is het spel?",
                gameLabel = "Spel",
                inDeck = "in de stapel",
                readMore = "Lees meer",
                showLess = "Toon minder",
                fromTheRulebook = "Uit de spelregels",
            ),
            Language.GERMAN to UiStrings(
                iUnderstand = "Ich verstehe Deutsch",
                gameLanguageQuestion = "In welcher Sprache ist das Spiel?",
                gameLabel = "Spiel",
                inDeck = "im Stapel",
                readMore = "Mehr lesen",
                showLess = "Weniger anzeigen",
                fromTheRulebook = "Aus der Spielregel",
            ),
            Language.POLISH to UiStrings(
                iUnderstand = "Rozumiem po polsku",
                gameLanguageQuestion = "W jakim języku jest gra?",
                gameLabel = "Gra",
                inDeck = "w talii",
                readMore = "Czytaj więcej",
                showLess = "Pokaż mniej",
                fromTheRulebook = "Z instrukcji",
            ),
        )

        operator fun get(language: Language): UiStrings = byLanguage.getValue(language)
    }
}

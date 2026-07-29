package nl.jjt.explodingkittenscompanion.data

/**
 * The handful of strings the app says on its own behalf, in each [Language].
 *
 * Deliberately not `composeResources` string resources: those follow the *device* locale,
 * but the language here is an explicit in-app choice that changes whenever the phone is
 * handed to another player.
 */
data class UiStrings(
    /**
     * The title printed on that edition's box. Strictly edition data rather than an app
     * string, but [UiStrings] is already the one block you edit per language (see CLAUDE.md,
     * "Adding a language") and a fifth per-language registry costs more than it explains.
     */
    val gameTitle: String,
    /** Cycled through all languages on the first setup step, before we know who is holding the phone. */
    val whichGameQuestion: String,
    val translateToQuestion: String,
    /** Subtitle of the "my language is the deck's language" row at the bottom of step two. */
    val sameLanguageHint: String,
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
                gameTitle = "Exploding Kittens",
                whichGameQuestion = "Which game are you playing?",
                translateToQuestion = "Translate the cards into…",
                sameLanguageHint = "I read English — just explain the cards",
                gameLabel = "Game",
                inDeck = "in the deck",
                readMore = "Read more",
                showLess = "Show less",
                fromTheRulebook = "From the rulebook",
            ),
            Language.DUTCH to UiStrings(
                gameTitle = "Exploding Kittens",
                whichGameQuestion = "Welk spel speel je?",
                translateToQuestion = "Vertaal de kaarten naar…",
                sameLanguageHint = "Ik lees Nederlands — leg de kaarten gewoon uit",
                gameLabel = "Spel",
                inDeck = "in de stapel",
                readMore = "Lees meer",
                showLess = "Toon minder",
                fromTheRulebook = "Uit de spelregels",
            ),
            Language.GERMAN to UiStrings(
                gameTitle = "Exploding Kittens",
                whichGameQuestion = "Welches Spiel spielst du?",
                translateToQuestion = "Karten übersetzen nach…",
                sameLanguageHint = "Ich lese Deutsch — erklär mir einfach die Karten",
                gameLabel = "Spiel",
                inDeck = "im Stapel",
                readMore = "Mehr lesen",
                showLess = "Weniger anzeigen",
                fromTheRulebook = "Aus der Spielregel",
            ),
            Language.POLISH to UiStrings(
                gameTitle = "Wybuchające Kotki",
                whichGameQuestion = "W którą grę grasz?",
                translateToQuestion = "Przetłumacz karty na…",
                sameLanguageHint = "Czytam po polsku — po prostu wyjaśnij karty",
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

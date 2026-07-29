package nl.jjt.explodingkittenscompanion.data

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Guards the promise that adding a language is cheap: if a translation is incomplete, the
 * build fails here instead of the app showing a blank tile or a dead "read more" button.
 */
class CardCatalogTest {

    @Test
    fun everyCardIsTranslatedIntoEveryLanguage() {
        for (language in Language.entries) {
            for (card in Card.entries) {
                val text = CardCatalog[language, card]
                assertTrue(text.name.isNotBlank(), "$language $card has no name")
                assertTrue(text.summary.isNotBlank(), "$language $card has no summary")
                assertTrue(text.details.isNotEmpty(), "$language $card has no details")
                assertTrue(text.rulebook.isNotBlank(), "$language $card has no rulebook text")
            }
        }
    }

    @Test
    fun cardNamesAreUniqueWithinALanguage() {
        for (language in Language.entries) {
            val names = Card.entries.map { CardCatalog[language, it].name }
            assertEquals(
                names.size,
                names.distinct().size,
                "$language has duplicate card names, so the grid would be ambiguous",
            )
        }
    }

    @Test
    fun everyLanguageHasUiStrings() {
        for (language in Language.entries) {
            assertTrue(UiStrings[language].iUnderstand.isNotBlank(), "$language has no UI strings")
        }
    }

    @Test
    fun deckAddsUpToFiftySixCards() {
        assertEquals(56, Card.entries.sumOf { it.countInDeck })
    }
}

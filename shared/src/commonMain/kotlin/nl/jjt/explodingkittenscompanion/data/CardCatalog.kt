package nl.jjt.explodingkittenscompanion.data

import nl.jjt.explodingkittenscompanion.data.cards.dutchCards
import nl.jjt.explodingkittenscompanion.data.cards.englishCards
import nl.jjt.explodingkittenscompanion.data.cards.germanCards
import nl.jjt.explodingkittenscompanion.data.cards.polishCards

/**
 * Every card, in every language.
 *
 * Register a new language here after adding its `Cards<Language>.kt`. `CardCatalogTest`
 * checks that no combination is missing, so a half-finished translation fails the build
 * rather than showing up as a blank tile.
 */
object CardCatalog {

    private val byLanguage: Map<Language, Map<Card, CardText>> = mapOf(
        Language.ENGLISH to englishCards,
        Language.DUTCH to dutchCards,
        Language.GERMAN to germanCards,
        Language.POLISH to polishCards,
    )

    operator fun get(language: Language, card: Card): CardText =
        byLanguage.getValue(language).getValue(card)
}

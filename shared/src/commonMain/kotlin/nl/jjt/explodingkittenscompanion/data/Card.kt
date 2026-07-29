package nl.jjt.explodingkittenscompanion.data

/**
 * The distinct card types in the Exploding Kittens base game.
 *
 * The deck holds 56 physical cards but only nine types. The five cat cards behave
 * identically and carry no instructions, so they collapse into [CAT_CARD] — which is how the
 * rulebooks present them too. Nine entries fit one screen, so nobody has to scroll or search
 * to find the card in their hand.
 *
 * Declaration order is the order shown in the grid: the cards you most urgently need to look
 * up come first.
 */
enum class Card(val countInDeck: Int) {
    EXPLODING_KITTEN(4),
    DEFUSE(6),
    NOPE(5),
    ATTACK(4),
    SKIP(4),
    FAVOR(4),
    SHUFFLE(4),
    SEE_THE_FUTURE(5),
    CAT_CARD(20),
}

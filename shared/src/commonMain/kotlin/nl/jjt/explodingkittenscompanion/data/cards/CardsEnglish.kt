package nl.jjt.explodingkittenscompanion.data.cards

import nl.jjt.explodingkittenscompanion.data.Card
import nl.jjt.explodingkittenscompanion.data.CardText

/** Rulebook text transcribed from `docs/rules-source/rules_english.pdf` (2023 printing). */
val englishCards: Map<Card, CardText> = mapOf(
    Card.EXPLODING_KITTEN to CardText(
        name = "Exploding Kitten",
        summary = "You are out of the game — unless you have a Defuse card.",
        details = listOf(
            "Show it to everyone straight away.",
            "Have a Defuse? Play it and you survive.",
            "No Defuse? You are dead. Discard your whole hand.",
        ),
        rulebook = "You must show this card immediately. Unless you have a Defuse Card, " +
            "you're dead. Discard all of your cards, including the Exploding Kitten.",
    ),
    Card.DEFUSE to CardText(
        name = "Defuse",
        summary = "Survive an Exploding Kitten and hide it back in the deck wherever you like.",
        details = listOf(
            "Only playable right after you draw an Exploding Kitten.",
            "You secretly choose where the Kitten goes back — top, bottom, anywhere.",
            "Don't reorder or peek at the other cards.",
            "Your turn ends; you do not draw again.",
        ),
        rulebook = "If you drew an Exploding Kitten, you can play this card instead of " +
            "dying. Place your Defuse Card in the Discard Pile.\n\n" +
            "Then take the Exploding Kitten, and without reordering or viewing the other " +
            "cards, secretly put it back in the Draw Pile anywhere you'd like.\n\n" +
            "Want to hurt the player right after you? Put the Kitten right on top of the " +
            "deck. If you'd like, hold the deck under the table so that no one else can " +
            "see where you put it.\n\n" +
            "Your turn is over after playing this card.",
    ),
    Card.NOPE to CardText(
        name = "Nope",
        summary = "Cancel the card someone just played, as if it was never played.",
        details = listOf(
            "Playable at any time, even when it is not your turn.",
            "Cannot stop an Exploding Kitten or a Defuse.",
            "A Nope on a Nope turns it back on again.",
        ),
        rulebook = "Stop any action except for an Exploding Kitten or a Defuse Card. " +
            "Imagine that any card beneath a Nope Card never existed.\n\n" +
            "A Nope can also be played on another Nope to negate it and create a Yup, and " +
            "so on.\n\n" +
            "A Nope can be played at any time before an action has begun, even if it's not " +
            "your turn. Any cards that have been Noped are lost. Leave them in the Discard " +
            "Pile.\n\n" +
            "You can even play a Nope on a SPECIAL COMBO.",
    ),
    Card.ATTACK to CardText(
        name = "Attack (2x)",
        summary = "End your turn without drawing — the next player must take 2 turns.",
        details = listOf(
            "You do not draw a card at all.",
            "The victim plays a full turn, then immediately plays another.",
            "If the victim plays Attack too, the turns pile onto the next player.",
        ),
        rulebook = "Do not draw any cards. Instead, immediately force the next player to " +
            "take 2 turns in a row. Play then continues from that player. The victim of " +
            "this card takes a turn as normal (play-or-pass then draw). Then, when their " +
            "first turn is over, it's their turn again.\n\n" +
            "If the victim of an Attack Card plays an Attack Card on any of their turns, " +
            "the new target must take any remaining turns plus the number of attacks on " +
            "the Attack Card just played (e.g. 4 turns, then 6, and so on).",
    ),
    Card.SKIP to CardText(
        name = "Skip",
        summary = "End your turn right now without drawing a card.",
        details = listOf(
            "Safest way out when you think the top card is an Exploding Kitten.",
            "Against an Attack it only cancels 1 of the 2 turns — you need two Skips.",
        ),
        rulebook = "Immediately end your turn without drawing a card.\n\n" +
            "If you play a Skip Card as a defense to an Attack Card, it only ends 1 of the " +
            "2 turns. 2 Skip Cards would end both turns.",
    ),
    Card.FAVOR to CardText(
        name = "Favor",
        summary = "Pick a player — they must hand you one card.",
        details = listOf(
            "They choose which card you get, not you.",
            "Your turn continues; you still have to draw at the end.",
        ),
        rulebook = "Force any other player to give you 1 card from their hand. They choose " +
            "which card to give you.",
    ),
    Card.SHUFFLE to CardText(
        name = "Shuffle",
        summary = "Shuffle the draw pile.",
        details = listOf(
            "Use it when you know an Exploding Kitten is near the top.",
            "Your turn continues; you still have to draw at the end.",
        ),
        rulebook = "Shuffle the Draw Pile thoroughly. (Useful when you know there's an " +
            "Exploding Kitten coming.)",
    ),
    Card.SEE_THE_FUTURE to CardText(
        name = "See the Future (3x)",
        summary = "Secretly look at the top 3 cards of the deck.",
        details = listOf(
            "Put them back in exactly the same order.",
            "Do not show them to anyone.",
            "Your turn continues; you still have to draw at the end.",
        ),
        rulebook = "Privately view the top 3 cards from the Draw Pile and put them back in " +
            "the same order. Don't show the cards to the other players.",
    ),
    Card.CAT_CARD to CardText(
        name = "Cat Cards",
        summary = "Does nothing alone. Two matching cats let you steal a random card.",
        details = listOf(
            "Any card with just a cat picture and no instructions is a Cat Card.",
            "Two of a kind: steal a random card from a player of your choice.",
            "Three of a kind: name the card you want — if they have it, they must hand it over.",
            "Five different cards: take any card you like from the discard pile.",
            "Combos work with any matching cards, not just cats.",
        ),
        rulebook = "These cards are powerless on their own, but if you collect any 2 " +
            "matching Cat Cards, you can play them as a Pair to steal a random card from " +
            "any player. They can also be used in Special Combos.\n\n" +
            "TWO OF A KIND\nPlaying matching Pairs of Cat Cards (where you get to steal a " +
            "random card from another player) no longer only applies to pairs of Cat " +
            "Cards. It now applies to ANY pair of cards with the same title (a pair of " +
            "Shuffle Cards, a pair of Skip Cards, etc). Ignore the instructions on the " +
            "cards when you play a combo.\n\n" +
            "THREE OF A KIND\nWhen you play 3 matching cards (any 3 cards with the same " +
            "title), you get to pick a player and name a card. If they have that card, " +
            "they must give one to you. If they don't have it, you get nothing. Ignore the " +
            "instructions on the cards when you play a combo.",
    ),
)

package nl.jjt.explodingkittenscompanion.ui

import androidx.compose.ui.graphics.Color
import explodingkittenscompanion.shared.generated.resources.Res
import explodingkittenscompanion.shared.generated.resources.ic_attack
import explodingkittenscompanion.shared.generated.resources.ic_bomb
import explodingkittenscompanion.shared.generated.resources.ic_cat
import explodingkittenscompanion.shared.generated.resources.ic_defuse
import explodingkittenscompanion.shared.generated.resources.ic_favor
import explodingkittenscompanion.shared.generated.resources.ic_nope
import explodingkittenscompanion.shared.generated.resources.ic_see_future
import explodingkittenscompanion.shared.generated.resources.ic_shuffle
import explodingkittenscompanion.shared.generated.resources.ic_skip
import nl.jjt.explodingkittenscompanion.data.Card
import org.jetbrains.compose.resources.DrawableResource

/**
 * How a [Card] looks, independent of language.
 *
 * The colours mirror the icon colours the rulebooks print next to each card, so what the
 * player sees on paper and what they see on screen line up.
 */
data class CardStyle(val color: Color, val icon: DrawableResource)

val Card.style: CardStyle
    get() = when (this) {
        Card.EXPLODING_KITTEN -> CardStyle(Ek.Black, Res.drawable.ic_bomb)
        Card.DEFUSE -> CardStyle(Ek.Lime, Res.drawable.ic_defuse)
        Card.NOPE -> CardStyle(Ek.Red, Res.drawable.ic_nope)
        Card.ATTACK -> CardStyle(Ek.Orange, Res.drawable.ic_attack)
        Card.SKIP -> CardStyle(Ek.Sky, Res.drawable.ic_skip)
        Card.FAVOR -> CardStyle(Ek.Charcoal, Res.drawable.ic_favor)
        Card.SHUFFLE -> CardStyle(Ek.Taupe, Res.drawable.ic_shuffle)
        Card.SEE_THE_FUTURE -> CardStyle(Ek.Pink, Res.drawable.ic_see_future)
        Card.CAT_CARD -> CardStyle(Ek.Amber, Res.drawable.ic_cat)
    }

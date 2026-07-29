package nl.jjt.explodingkittenscompanion.ui

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import nl.jjt.explodingkittenscompanion.data.Card
import nl.jjt.explodingkittenscompanion.data.CardCatalog
import nl.jjt.explodingkittenscompanion.data.Language
import nl.jjt.explodingkittenscompanion.data.UiStrings
import org.jetbrains.compose.resources.painterResource

/** Three columns times three rows is exactly the nine card types. */
private const val COLUMNS = 3

/**
 * The main screen: every card in the deck, at a glance.
 *
 * Each tile leads with the name as printed on the physical card, because that is the string
 * the player is matching against what is in their hand. Their own language sits underneath
 * in smaller type, which answers a good share of lookups before anyone taps anything.
 *
 * All nine fit without scrolling, so there is nothing to search and nothing to swipe past.
 */
@Composable
fun CardGridScreen(
    gameLanguage: Language,
    understood: Language,
    onUnderstoodChange: (Language) -> Unit,
    onChangeGameLanguage: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var selected by remember { mutableStateOf<Card?>(null) }
    val strings = UiStrings[understood]

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Ek.Cream)
            .safeContentPadding(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = 560.dp)
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 16.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // The pill gives up space first; the switcher must never wrap, since it is what
            // the next player reaches for.
            Box(Modifier.weight(1f)) {
                GameLanguagePill(
                    label = strings.gameLabel,
                    language = gameLanguage.nativeName,
                    onClick = onChangeGameLanguage,
                )
            }
            LanguageSwitcher(selected = understood, onSelect = onUnderstoodChange)
        }

        // A fixed 3x3 that fills the height, not a scrolling grid: there are always exactly
        // nine cards, and the whole point is that nobody has to scroll or search for one.
        // Capped in width so it stays a grid of cards on a desktop browser rather than
        // nine billboards.
        Column(
            modifier = Modifier
                .fillMaxSize()
                .widthIn(max = 560.dp)
                .align(Alignment.CenterHorizontally)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Card.entries.chunked(COLUMNS).forEachIndexed { rowIndex, row ->
                Row(
                    modifier = Modifier.fillMaxWidth().weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    row.forEachIndexed { columnIndex, card ->
                        CardTile(
                            card = card,
                            gameLanguage = gameLanguage,
                            understood = understood,
                            index = rowIndex * COLUMNS + columnIndex,
                            onClick = { selected = card },
                            modifier = Modifier.weight(1f).fillMaxHeight(),
                        )
                    }
                }
            }
        }
    }

    selected?.let { card ->
        CardDetailSheet(
            card = card,
            gameLanguage = gameLanguage,
            understood = understood,
            onUnderstoodChange = onUnderstoodChange,
            onDismiss = { selected = null },
        )
    }
}

@Composable
private fun CardTile(
    card: Card,
    gameLanguage: Language,
    understood: Language,
    index: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()
    val pressScale by animateFloatAsState(
        targetValue = if (pressed) 0.94f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
    )

    // Staggered entrance, so the grid deals itself out like a hand of cards.
    var revealed by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(index * 45L)
        revealed = true
    }
    val reveal by animateFloatAsState(if (revealed) 1f else 0f, tween(280))

    Surface(
        onClick = onClick,
        interactionSource = interactionSource,
        shape = MaterialTheme.shapes.medium,
        color = Ek.Paper,
        border = BorderStroke(EkBorderWidth, Ek.Ink),
        modifier = modifier
            .graphicsLayer {
                alpha = reveal
                scaleX = pressScale * (0.9f + 0.1f * reveal)
                scaleY = pressScale * (0.9f + 0.1f * reveal)
            },
    ) {
        Box(Modifier.fillMaxSize().padding(horizontal = 4.dp, vertical = 8.dp)) {
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CardIcon(card, size = 38.dp)
                // The name as printed on the physical card leads, because that is what the
                // player is matching against the card in their hand.
                Text(
                    text = CardCatalog[gameLanguage, card].name,
                    style = MaterialTheme.typography.titleSmall,
                    fontSize = 12.sp,
                    lineHeight = 14.sp,
                    textAlign = TextAlign.Center,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 8.dp),
                )
                if (understood != gameLanguage) {
                    Text(
                        text = CardCatalog[understood, card].name,
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 10.sp,
                        lineHeight = 12.sp,
                        color = Ek.Muted,
                        textAlign = TextAlign.Center,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(top = 3.dp),
                    )
                }
            }
            // How many are in the deck — useful when working out the odds, and it stops the
            // tile from looking half empty.
            Text(
                text = "${card.countInDeck}×",
                style = MaterialTheme.typography.labelMedium,
                color = Ek.Muted,
                modifier = Modifier.align(Alignment.BottomCenter),
            )
        }
    }
}

/** The card's icon on its colour disc — the same pairing the rulebooks use. */
@Composable
fun CardIcon(card: Card, size: Dp) {
    val style = card.style
    Box(
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
            .background(style.color),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(style.icon),
            contentDescription = null,
            colorFilter = ColorFilter.tint(Ek.Paper),
            modifier = Modifier.size(size * 0.55f),
        )
    }
}

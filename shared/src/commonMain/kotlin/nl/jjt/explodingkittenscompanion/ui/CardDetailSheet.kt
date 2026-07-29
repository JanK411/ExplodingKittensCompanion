package nl.jjt.explodingkittenscompanion.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import nl.jjt.explodingkittenscompanion.data.Card
import nl.jjt.explodingkittenscompanion.data.CardCatalog
import nl.jjt.explodingkittenscompanion.data.Language
import nl.jjt.explodingkittenscompanion.data.UiStrings

/**
 * What the card does, in the player's own language.
 *
 * A sheet rather than a screen so the grid stays visible behind it — the player keeps their
 * bearings and one tap outside gets them back.
 *
 * It carries its own [LanguageSwitcher]: the sheet is modal, so the one in the top bar is
 * behind the scrim precisely when the phone gets handed to the next player.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardDetailSheet(
    card: Card,
    gameLanguage: Language,
    understood: Language,
    onUnderstoodChange: (Language) -> Unit,
    onDismiss: () -> Unit,
) {
    val strings = UiStrings[understood]
    val printed = CardCatalog[gameLanguage, card]
    val explained = CardCatalog[understood, card]
    var showRulebook by remember { mutableStateOf(false) }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
        containerColor = Ek.Paper,
        shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp)
                .padding(bottom = 32.dp),
        ) {
            LanguageSwitcher(
                selected = understood,
                onSelect = onUnderstoodChange,
                modifier = Modifier.align(Alignment.End),
            )

            Row(
                modifier = Modifier.padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                CardIcon(card, size = 64.dp)
                Column(Modifier.weight(1f)) {
                    Text(
                        text = printed.name,
                        style = MaterialTheme.typography.headlineSmall,
                    )
                    Text(
                        text = "${card.countInDeck} ${strings.inDeck}",
                        style = MaterialTheme.typography.bodySmall,
                        color = Ek.Muted,
                    )
                }
            }

            // The one line that answers the question people opened the app for.
            Text(
                text = explained.summary,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 20.dp),
            )

            explained.details.forEach { detail ->
                Row(
                    modifier = Modifier.padding(top = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    Box(
                        Modifier
                            .padding(top = 7.dp)
                            .size(7.dp)
                            .clip(CircleShape)
                            .background(card.style.color)
                    )
                    Text(text = detail, style = MaterialTheme.typography.bodyMedium)
                }
            }

            HorizontalDivider(
                modifier = Modifier.padding(top = 24.dp),
                thickness = 2.dp,
                color = Ek.Cream,
            )

            Text(
                text = if (showRulebook) strings.showLess else strings.readMore,
                style = MaterialTheme.typography.labelLarge,
                color = Ek.Red,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.small)
                    .clickable { showRulebook = !showRulebook }
                    .padding(vertical = 14.dp),
            )

            AnimatedVisibility(
                visible = showRulebook,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically(),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(MaterialTheme.shapes.medium)
                        .background(Ek.Cream)
                        .padding(16.dp),
                ) {
                    Text(
                        text = strings.fromTheRulebook.uppercase(),
                        style = MaterialTheme.typography.labelMedium,
                        color = Ek.Muted,
                    )
                    Text(
                        text = explained.rulebook,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 10.dp),
                    )
                }
            }
        }
    }
}

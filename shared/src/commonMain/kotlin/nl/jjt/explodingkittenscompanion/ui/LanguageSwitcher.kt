package nl.jjt.explodingkittenscompanion.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import nl.jjt.explodingkittenscompanion.data.Language

/**
 * Switches the language the app explains cards in, without leaving the screen.
 *
 * Exists for one moment in a game: a player hands the phone to someone who reads a different
 * language. That has to cost one tap and must not close whatever card is open, so this sits
 * both in the top bar and inside the detail sheet — the sheet is modal, so the top bar is
 * unreachable behind the scrim exactly when it is most needed.
 *
 * Two-letter codes rather than full names, deliberately: this is a supporting control and
 * should stay visually quiet next to the cards.
 */
@Composable
fun LanguageSwitcher(
    selected: Language,
    onSelect: (Language) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(50))
            .background(Ek.Cream)
            .border(2.dp, Ek.Ink, RoundedCornerShape(50))
            .padding(3.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        Language.entries.forEach { language ->
            LanguageChip(
                code = language.code,
                name = language.nativeName,
                selected = language == selected,
                onClick = { onSelect(language) },
            )
        }
    }
}

@Composable
private fun LanguageChip(code: String, name: String, selected: Boolean, onClick: () -> Unit) {
    val background by animateColorAsState(if (selected) Ek.Red else Color.Transparent)
    val content by animateColorAsState(if (selected) Color.White else Ek.Muted)
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(background)
            .clickable(onClick = onClick)
            .padding(horizontal = 10.dp, vertical = 6.dp)
            .semantics { contentDescription = name },
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = code,
            style = MaterialTheme.typography.labelLarge,
            color = content,
            softWrap = false,
        )
    }
}

/**
 * Shows which language the deck on the table is printed in. Tapping it reopens setup, which
 * is rare enough not to deserve its own always-visible control.
 */
@Composable
fun GameLanguagePill(label: String, language: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(Ek.Ink)
            .clickable(onClick = onClick)
            .padding(horizontal = 14.dp, vertical = 7.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = label.uppercase(),
            style = MaterialTheme.typography.labelMedium,
            color = Ek.Muted,
            maxLines = 1,
        )
        Text(
            text = language,
            style = MaterialTheme.typography.labelLarge,
            color = Color.White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

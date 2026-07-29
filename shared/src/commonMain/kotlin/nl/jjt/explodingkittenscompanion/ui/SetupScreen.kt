package nl.jjt.explodingkittenscompanion.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import nl.jjt.explodingkittenscompanion.data.Language
import nl.jjt.explodingkittenscompanion.data.UiStrings

/**
 * Picks both languages in two taps.
 *
 * The order matters. We cannot word a question in a language the player has not told us
 * they read, so the first step asks who is holding the phone using nothing but the language
 * names themselves — each written in its own language, and a prompt that cycles through all
 * four so at least one reading of it always makes sense. Everything after that first tap is
 * in a language we know they understand.
 */
@Composable
fun SetupScreen(
    understood: Language?,
    onUnderstoodPicked: (Language) -> Unit,
    onGamePicked: (Language) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .safeContentPadding()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AppTitle()

        Box(Modifier.padding(top = 32.dp, bottom = 20.dp), contentAlignment = Alignment.Center) {
            AnimatedContent(
                targetState = understood,
                transitionSpec = {
                    (fadeIn(tween(220)) + slideInVertically { it / 3 })
                        .togetherWith(fadeOut(tween(120)) + slideOutVertically { -it / 3 })
                },
            ) { language ->
                if (language == null) {
                    CyclingUnderstandPrompt()
                } else {
                    Text(
                        text = UiStrings[language].gameLanguageQuestion,
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }

        Column(
            modifier = Modifier.widthIn(max = 420.dp).fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Language.entries.forEach { language ->
                LanguageButton(
                    language = language,
                    onClick = {
                        if (understood == null) onUnderstoodPicked(language) else onGamePicked(language)
                    },
                )
            }
        }
    }
}

/**
 * "I understand …" written in each language in turn. Solves the chicken-and-egg of asking a
 * question before knowing which language the reader speaks, and gives the first screen a bit
 * of life.
 */
@Composable
private fun CyclingUnderstandPrompt() {
    var index by remember { mutableIntStateOf(0) }
    LaunchedEffect(Unit) {
        while (true) {
            delay(1800)
            index = (index + 1) % Language.entries.size
        }
    }
    AnimatedContent(
        targetState = index,
        // The incoming line waits for the outgoing one to clear; overlapping fades would
        // render two languages on top of each other, which is worse than either alone.
        transitionSpec = {
            fadeIn(tween(300, delayMillis = 300)).togetherWith(fadeOut(tween(300)))
        },
    ) { current ->
        Text(
            text = UiStrings[Language.entries[current]].iUnderstand,
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun LanguageButton(language: Language, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        shape = MaterialTheme.shapes.large,
        border = BorderStroke(EkBorderWidth, Ek.Ink),
        colors = CardDefaults.cardColors(containerColor = Ek.Paper),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = language.nativeName,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(PaddingValues(horizontal = 24.dp, vertical = 18.dp)),
        )
    }
}

/** Title, with a lazy tilt borrowed from the box art. */
@Composable
private fun AppTitle() {
    val wobble by rememberInfiniteTransition().animateFloat(
        initialValue = -1.5f,
        targetValue = 1.5f,
        animationSpec = infiniteRepeatable(tween(2600, easing = LinearEasing), RepeatMode.Reverse),
    )
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "EXPLODING",
            style = MaterialTheme.typography.displaySmall,
            color = Ek.Red,
            modifier = Modifier.graphicsLayer { rotationZ = wobble },
        )
        Text(
            text = "KITTENS",
            style = MaterialTheme.typography.displaySmall,
            color = Ek.Ink,
            modifier = Modifier.graphicsLayer { rotationZ = -wobble },
        )
        Text(
            text = "COMPANION",
            style = MaterialTheme.typography.labelMedium,
            color = Ek.Muted,
        )
    }
}

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
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import explodingkittenscompanion.shared.generated.resources.Res
import explodingkittenscompanion.shared.generated.resources.ic_cat_bomb
import kotlinx.coroutines.delay
import nl.jjt.explodingkittenscompanion.data.Card as DeckCard
import nl.jjt.explodingkittenscompanion.data.CardCatalog
import nl.jjt.explodingkittenscompanion.data.Language
import nl.jjt.explodingkittenscompanion.data.UiStrings
import org.jetbrains.compose.resources.painterResource

/**
 * Picks both languages in two taps.
 *
 * The deck comes first, because that is the order the player knows things in: the box is
 * already on the table before anyone decides who is reading the phone. The two steps also
 * look nothing alike on purpose — when both asked the same four-button question, people read
 * the second one as the first tap not having registered and answered it again.
 *
 * We still cannot word a question in a language the player has told us nothing about, so the
 * headings cycle through all four languages until we know better; everything after the second
 * tap is in a language we know they read.
 */
@Composable
fun SetupScreen(
    gameLanguage: Language?,
    onGamePicked: (Language) -> Unit,
    onUnderstoodPicked: (Language) -> Unit,
    onBack: () -> Unit,
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

        AnimatedContent(
            targetState = gameLanguage,
            transitionSpec = {
                (fadeIn(tween(220)) + slideInVertically { it / 3 })
                    .togetherWith(fadeOut(tween(120)) + slideOutVertically { -it / 3 })
            },
        ) { game ->
            if (game == null) {
                GameStep(onGamePicked = onGamePicked)
            } else {
                TranslationStep(
                    gameLanguage = game,
                    onUnderstoodPicked = onUnderstoodPicked,
                    onBack = onBack,
                )
            }
        }
    }
}

/**
 * Accents for the boxes, handed out in [Language] declaration order and wrapped around, so
 * adding a language is still a one-line change and never a colour decision.
 */
private val BoxAccents = listOf(Ek.Red, Ek.Sky, Ek.Lime, Ek.Amber)

/** Step one: which box is on the table. Deliberately no language list anywhere on it. */
@Composable
private fun GameStep(onGamePicked: (Language) -> Unit) {
    Column(
        modifier = Modifier.widthIn(max = 420.dp).fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(Modifier.padding(top = 28.dp, bottom = 20.dp), contentAlignment = Alignment.Center) {
            CyclingPrompt { it.whichGameQuestion }
        }
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Language.entries.chunked(2).forEachIndexed { rowIndex, row ->
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    row.forEachIndexed { columnIndex, language ->
                        val index = rowIndex * 2 + columnIndex
                        GameBoxTile(
                            language = language,
                            accent = BoxAccents[index % BoxAccents.size],
                            onClick = { onGamePicked(language) },
                            modifier = Modifier.weight(1f),
                        )
                    }
                }
            }
        }
    }
}

/**
 * One edition, shown the way it sits on the table: the title as printed on that box, with the
 * language name and a sample card name underneath. The sample is [DeckCard.DEFUSE] because its
 * name differs in all four languages, so the tiles stay distinguishable even where the box
 * title does not (the Dutch and German editions are both titled "Exploding Kittens").
 */
@Composable
private fun GameBoxTile(
    language: Language,
    accent: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = onClick,
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(EkBorderWidth, Ek.Ink),
        colors = CardDefaults.cardColors(containerColor = Ek.Paper),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(Res.drawable.ic_cat_bomb),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Ek.Ink),
                modifier = Modifier.size(40.dp),
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 52.dp)
                    .clip(MaterialTheme.shapes.small)
                    .background(accent)
                    .border(EkBorderWidth, Ek.Ink, MaterialTheme.shapes.small)
                    .padding(horizontal = 6.dp, vertical = 8.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = UiStrings[language].gameTitle,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 14.sp,
                    lineHeight = 16.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Text(
                text = language.nativeName,
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 10.dp),
            )
            Text(
                text = CardCatalog[language, DeckCard.DEFUSE].name,
                style = MaterialTheme.typography.bodySmall,
                fontSize = 11.sp,
                color = Ek.Muted,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

/**
 * Step two: who is reading the phone. The deck's own language is pulled out of the list and
 * parked under a divider, because picking it is not asking for a translation — it is asking
 * for the rules explained.
 */
@Composable
private fun TranslationStep(
    gameLanguage: Language,
    onUnderstoodPicked: (Language) -> Unit,
    onBack: () -> Unit,
) {
    Column(
        modifier = Modifier.widthIn(max = 420.dp).fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // The same pill that means "tap to reopen the game choice" on the grid screen, so it
        // needs no new icon and doubles as confirmation of what was just picked.
        Box(Modifier.padding(top = 24.dp)) {
            GameLanguagePill(
                label = UiStrings[gameLanguage].gameLabel,
                language = gameLanguage.nativeName,
                onClick = onBack,
            )
        }

        Box(Modifier.padding(top = 20.dp, bottom = 18.dp), contentAlignment = Alignment.Center) {
            CyclingPrompt { it.translateToQuestion }
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Language.entries.filter { it != gameLanguage }.forEach { language ->
                LanguageRow(language = language, onClick = { onUnderstoodPicked(language) })
            }

            Spacer(
                Modifier
                    .padding(vertical = 6.dp)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Ek.Muted),
            )

            LanguageRow(
                language = gameLanguage,
                hint = UiStrings[gameLanguage].sameLanguageHint,
                onClick = { onUnderstoodPicked(gameLanguage) },
            )
        }
    }
}

@Composable
private fun LanguageRow(language: Language, hint: String? = null, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        shape = MaterialTheme.shapes.large,
        border = BorderStroke(EkBorderWidth, Ek.Ink),
        colors = CardDefaults.cardColors(containerColor = Ek.Paper),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            LanguageFlag(language)
            Column {
                Text(
                    text = language.nativeName,
                    style = MaterialTheme.typography.headlineSmall,
                )
                if (hint != null) {
                    Text(
                        text = hint,
                        style = MaterialTheme.typography.bodySmall,
                        color = Ek.Muted,
                    )
                }
            }
        }
    }
}

/**
 * Decorative only — [Language.nativeName] next to it is the real label, so this carries no
 * content description. The border is not just styling: the English and Polish flags are
 * half white, which would otherwise dissolve into [Ek.Paper].
 */
@Composable
private fun LanguageFlag(language: Language) {
    val shape = RoundedCornerShape(4.dp)
    Box(
        modifier = Modifier
            .size(width = 30.dp, height = 21.dp)
            .border(1.5.dp, Ek.Ink, shape)
            .padding(1.5.dp)
            .clip(RoundedCornerShape(3.dp)),
    ) {
        Image(
            painter = painterResource(language.flag),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize(),
        )
    }
}

/**
 * A heading written in each language in turn. Solves the chicken-and-egg of asking a question
 * before knowing which language the reader speaks — it applies to both steps, since neither
 * happens after we know — and gives the setup screens a bit of life.
 */
@Composable
private fun CyclingPrompt(text: (UiStrings) -> String) {
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
            text = text(UiStrings[Language.entries[current]]),
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
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

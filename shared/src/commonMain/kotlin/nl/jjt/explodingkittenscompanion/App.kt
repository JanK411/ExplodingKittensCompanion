package nl.jjt.explodingkittenscompanion

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import nl.jjt.explodingkittenscompanion.data.Language
import nl.jjt.explodingkittenscompanion.ui.CardGridScreen
import nl.jjt.explodingkittenscompanion.ui.Ek
import nl.jjt.explodingkittenscompanion.ui.ExplodingKittensTheme
import nl.jjt.explodingkittenscompanion.ui.SetupScreen

/**
 * Helps people who don't read the language a deck is printed in play Exploding Kittens
 * anyway: find the card you're holding, tap it, read what it does in your own language.
 *
 * Two pieces of state and nothing else. Nothing is persisted — setup costs two taps, which
 * is cheaper than a storage abstraction across Android, web and iOS.
 */
@Composable
@Preview
fun App() {
    var gameLanguage by remember { mutableStateOf<Language?>(null) }
    var understood by remember { mutableStateOf<Language?>(null) }

    ExplodingKittensTheme {
        Surface(Modifier.fillMaxSize().background(Ek.Cream), color = Ek.Cream) {
            AnimatedContent(
                targetState = gameLanguage to understood,
                // Key on which screen we are on, not on the languages. Without this, changing
                // the understood language counts as new content, so the grid is rebuilt from
                // scratch and any open card sheet is thrown away — which is precisely the
                // moment the phone is being handed to another player.
                contentKey = { (game, mine) -> game != null && mine != null },
                transitionSpec = { fadeIn(tween(260)).togetherWith(fadeOut(tween(160))) },
            ) { (game, mine) ->
                if (game == null || mine == null) {
                    SetupScreen(
                        gameLanguage = game,
                        onGamePicked = { gameLanguage = it },
                        onUnderstoodPicked = { understood = it },
                        onBack = { gameLanguage = null },
                    )
                } else {
                    CardGridScreen(
                        gameLanguage = game,
                        understood = mine,
                        onUnderstoodChange = { understood = it },
                        onChangeGameLanguage = {
                            gameLanguage = null
                            understood = null
                        },
                    )
                }
            }
        }
    }
}

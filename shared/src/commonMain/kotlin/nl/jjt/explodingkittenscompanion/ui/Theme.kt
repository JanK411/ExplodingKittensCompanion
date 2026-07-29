package nl.jjt.explodingkittenscompanion.ui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * The game's own look: cream card stock, a hot red, and fat black outlines around
 * everything.
 *
 * Deliberately light-only. Exploding Kittens has one visual identity and a dark variant
 * would read as a different product; the app is also used under bright table lighting.
 */
object Ek {
    val Red = Color(0xFFD9271E)
    val Cream = Color(0xFFFDF6E3)
    val Ink = Color(0xFF1A1A1A)
    val Paper = Color(0xFFFFFFFF)
    val Muted = Color(0xFF7A7166)

    /** Per-card accents, matching the icon colours the rulebooks use. */
    val Black = Color(0xFF1A1A1A)
    val Charcoal = Color(0xFF463F3A)
    val Lime = Color(0xFF9FBF1C)
    val Orange = Color(0xFFF58220)
    val Sky = Color(0xFF2E9BD6)
    val Taupe = Color(0xFF8A7E72)
    val Pink = Color(0xFFED4E9E)
    val Amber = Color(0xFFF2B705)
}

/** Thickness of the black outline that gives cards their sticker-like look. */
val EkBorderWidth = 3.dp

private val EkColorScheme = lightColorScheme(
    primary = Ek.Red,
    onPrimary = Color.White,
    secondary = Ek.Ink,
    onSecondary = Color.White,
    background = Ek.Cream,
    onBackground = Ek.Ink,
    surface = Ek.Paper,
    onSurface = Ek.Ink,
    surfaceVariant = Ek.Cream,
    onSurfaceVariant = Ek.Muted,
    outline = Ek.Ink,
)

private val EkShapes = Shapes(
    extraSmall = RoundedCornerShape(8.dp),
    small = RoundedCornerShape(12.dp),
    medium = RoundedCornerShape(18.dp),
    large = RoundedCornerShape(24.dp),
    extraLarge = RoundedCornerShape(28.dp),
)

/**
 * Heavy, tightly-tracked, uppercase-friendly type. The game's own font is a condensed bold
 * sans; the platform default at [FontWeight.Black] is the closest thing available without
 * shipping a font file.
 */
private val EkTypography = Typography().run {
    copy(
        displaySmall = displaySmall.copy(fontWeight = FontWeight.Black, letterSpacing = (-0.5).sp),
        headlineMedium = headlineMedium.copy(fontWeight = FontWeight.Black, letterSpacing = (-0.5).sp),
        headlineSmall = headlineSmall.copy(fontWeight = FontWeight.Black, letterSpacing = (-0.3).sp),
        titleLarge = titleLarge.copy(fontWeight = FontWeight.Black),
        titleMedium = titleMedium.copy(fontWeight = FontWeight.Black),
        titleSmall = titleSmall.copy(fontWeight = FontWeight.Bold),
        labelLarge = TextStyle(fontWeight = FontWeight.Black, fontSize = 13.sp, letterSpacing = 1.sp),
        labelMedium = TextStyle(fontWeight = FontWeight.Black, fontSize = 11.sp, letterSpacing = 1.2.sp),
        bodyLarge = bodyLarge.copy(fontWeight = FontWeight.Medium),
    )
}

@Composable
fun ExplodingKittensTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = EkColorScheme,
        shapes = EkShapes,
        typography = EkTypography,
        content = content,
    )
}

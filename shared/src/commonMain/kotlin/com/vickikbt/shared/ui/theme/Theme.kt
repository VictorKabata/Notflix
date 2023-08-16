package com.vickikbt.shared.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import com.vickikbt.shared.utils.WindowSize

private val DarkColorPalette = darkColorScheme(
    primary = DarkPrimaryColor,
    secondary = DarkPrimaryColor,
    surface = DarkSurface,
    onSurface = DarkTextPrimary
)

private val LightColorPalette = lightColorScheme(
    primary = PrimaryColor,
    secondary = PrimaryColor,
    surface = Surface,
    onSurface = TextPrimary
)

val LocalWindowSize = compositionLocalOf { WindowSize.COMPACT }

@Composable
fun NotflixTheme(
    windowSize: WindowSize,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorPalette else LightColorPalette

    CompositionLocalProvider(LocalWindowSize provides windowSize) {
        MaterialTheme(
            colorScheme = colorScheme,
            // typography = Typography,
            shapes = Shapes
        ) {
            content()
        }
    }
}

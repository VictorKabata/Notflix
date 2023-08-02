package com.vickikbt.shared.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.vickikbt.shared.presentation.ui.theme.DarkPrimaryColor
import com.vickikbt.shared.presentation.ui.theme.DarkSurface
import com.vickikbt.shared.presentation.ui.theme.DarkTextPrimary
import com.vickikbt.shared.presentation.ui.theme.PrimaryColor
import com.vickikbt.shared.presentation.ui.theme.Surface
import com.vickikbt.shared.presentation.ui.theme.TextPrimary

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

@Composable
fun NotflixTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorPalette else LightColorPalette

    MaterialTheme(
        colorScheme = colorScheme,
        // typography = Typography,
        shapes = Shapes,
        content = content
    )
}

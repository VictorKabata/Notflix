package com.vickikbt.notflix.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.SystemUiController

private val DarkColorPalette = darkColors(
    primary = DarkPrimaryColor,
    surface = DarkSurface,
    onSurface = DarkTextPrimary
)

private val LightColorPalette = lightColors(
    primary = PrimaryColor,
    surface = Surface,
    onSurface = TextPrimary
)

@Composable
fun NotflixTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    systemUiController: SystemUiController,
    content: @Composable () -> Unit
) {
    SideEffect {
        if (darkTheme) {
            systemUiController.setStatusBarColor(color = DarkSurface, darkIcons = !darkTheme)
        } else {
            systemUiController.setStatusBarColor(color = Surface, darkIcons = darkTheme)
        }
    }

    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )

}
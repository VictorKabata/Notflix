@file:OptIn(ExperimentalCoilApi::class)

package com.vickbt.composeApp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import com.vickbt.composeApp.domain.utils.sdpSspScalingRatio
import network.chaintech.sdpcomposemultiplatform.SDPConfig
import com.vickbt.composeApp.utils.getAsyncImageLoader

private val DarkColorPalette = darkColorScheme(
    primary = Color(0xFFE50914), // Notflix Red
    onPrimary = Color(0xFFFFFFFF), // White
    primaryContainer = Color(0xFF7D020A), // Darker Red for buttons
    onPrimaryContainer = Color(0xFFFFFFFF), // White on dark red

    secondary = Color(0xFF121212), // Notflix Background Dark Gray
    onSecondary = Color(0xFFE0E0E0), // Light Gray Text

    background = Color(0xFF000000), // Black
    onBackground = Color(0xFFE0E0E0), // Light Gray Text

    surface = Color(0xFF121212), // Dark Gray
    onSurface = Color(0xFFE0E0E0), // Light Gray Text

    error = Color(0xFFCF6679), // Default Material Red
    onError = Color(0xFFFFFFFF) // White on Error
)

private val LightColorPalette = lightColorScheme(
    primary = Color(0xFFE50914), // Notflix Red
    onPrimary = Color(0xFFFFFFFF), // White on Red
    primaryContainer = Color(0xFFFFDAD5), // Light Red for containers
    onPrimaryContainer = Color(0xFF410003), // Dark Red on Light Red

    secondary = Color(0xFFF5F5F5), // Light Gray for background and surfaces
    onSecondary = Color(0xFF121212), // Dark Gray text on Light Gray

    background = Color(0xFFFFFFFF), // Pure White Background
    onBackground = Color(0xFF121212), // Dark Gray Text

    surface = Color(0xFFF5F5F5), // Light Gray Surface
    onSurface = Color(0xFF121212), // Dark Gray Text

    error = Color(0xFFB3261E), // Material's Default Red
    onError = Color(0xFFFFFFFF) // White on Error
)

@Composable
fun NotflixTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    SDPConfig.setScalingRatio(sdpSspScalingRatio)

    val colorScheme = if (darkTheme) DarkColorPalette else LightColorPalette

    setSingletonImageLoaderFactory { context ->
        context.getAsyncImageLoader()
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography(),
        shapes = Shapes()
    ) {
        content()
    }
}

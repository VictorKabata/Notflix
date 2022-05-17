package ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.rememberWindowState
import ui.screens.splash.SplashScreen
import ui.theme.NotflixDesktopTheme

@Composable
fun MainScreen(applicationScope: ApplicationScope) {
    Window(
        onCloseRequest = { applicationScope.exitApplication() },
        title = "Notflix",
        state = rememberWindowState(width = 800.dp, height = 600.dp)
    ) {
        val backgroundColor = if (MaterialTheme.colors.isLight) Color.Black else Color.White

        NotflixDesktopTheme {
            Surface(modifier = Modifier.background(color = backgroundColor)) {
                SplashScreen()
            }
        }
    }
}

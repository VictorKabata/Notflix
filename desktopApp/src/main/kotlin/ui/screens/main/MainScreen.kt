package ui.screens.main

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.rememberWindowState
import ui.components.NavigationRailBar
import ui.navigation.NavigationItem
import ui.screens.home.HomeScreen
import ui.theme.NotflixDesktopTheme

@Composable
fun MainScreen(applicationScope: ApplicationScope) {

    Window(
        onCloseRequest = { applicationScope.exitApplication() },
        title = "Notflix",
        state = rememberWindowState(width = 800.dp, height = 600.dp)
    ) {

        val topLevelDestinations = listOf(
            NavigationItem.Home,
            NavigationItem.Favorites,
            NavigationItem.Settings
        )

        NotflixDesktopTheme {
            Surface {
                Row {
                    NavigationRailBar(navRailItems = topLevelDestinations)

                    HomeScreen()
                }
            }
        }
    }
}

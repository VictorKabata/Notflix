package ui.screens.main

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import ui.components.NavigationRailBar
import ui.navigation.NavigationItem
import ui.screens.home.HomeScreen
import ui.theme.NotflixDesktopTheme

@Composable
fun MainScreen(applicationScope: ApplicationScope) {

    val appIcon = painterResource("n_logo.png")

    Window(
        onCloseRequest = { applicationScope.exitApplication() },
        title = "Notflix",
        icon = appIcon,
        state = rememberWindowState(
            position = WindowPosition.Aligned(Alignment.Center),
            width = Dp.Unspecified,
            height = Dp.Unspecified,
        )
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

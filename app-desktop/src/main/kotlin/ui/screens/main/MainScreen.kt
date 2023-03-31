package ui.screens.main

import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import com.vickikbt.shared.presentation.presenters.SharedMainPresenter
import koin
import ui.components.NavigationRailBar
import ui.navigation.Navigation
import ui.navigation.NavigationItem
import ui.navigation.rememberNavController
import ui.theme.NotflixDesktopTheme

@Composable
fun MainScreen(applicationScope: ApplicationScope, viewModel: SharedMainPresenter = koin.get()) {
    val appIcon = painterResource("n_logo.png")

    val theme = viewModel.appTheme.collectAsState().value

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
        val isDarkTheme = theme != 0

        val topLevelDestinations = listOf(
            NavigationItem.Home,
            NavigationItem.Favorites,
            NavigationItem.Settings
        )

        val navController by rememberNavController(startDestination = NavigationItem.Home.route)

        NotflixDesktopTheme(darkTheme = isDarkTheme) {
            Surface(color = MaterialTheme.colors.surface) {
                Row {
                    NavigationRailBar(
                        navController = navController,
                        navRailItems = topLevelDestinations
                    )

                    Navigation(navController = navController)
                }
            }
        }
    }
}

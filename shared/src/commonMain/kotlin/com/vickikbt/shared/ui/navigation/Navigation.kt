package com.vickikbt.shared.ui.navigation

import androidx.compose.runtime.Composable
import com.vickikbt.shared.presentation.ui.navigation.NavigationItem
import com.vickikbt.shared.ui.screens.details.DetailsScreen
import com.vickikbt.shared.ui.screens.favorites.FavoritesScreen
import com.vickikbt.shared.ui.screens.home.HomeScreen
import com.vickikbt.shared.ui.screens.settings.SettingsScreen
import com.vickikbt.shared.utils.WindowSize
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.path

@Composable
fun Navigation(navigator: Navigator, windowSize: WindowSize) {
    NavHost(navigator = navigator, initialRoute = NavigationItem.Home.route) {
        scene(NavigationItem.Home.route) {
            HomeScreen(navigator = navigator, windowSize = windowSize)
        }

        scene(NavigationItem.Favorites.route) {
            FavoritesScreen()
        }

        scene(NavigationItem.Settings.route) {
            SettingsScreen()
        }

        scene(NavigationItem.Details.route) { backStackEntry ->
            backStackEntry.path<Int>("id")?.let { movieId ->
                DetailsScreen(navigator = navigator, windowSize = windowSize, movieId = movieId)
            }
        }
    }
}

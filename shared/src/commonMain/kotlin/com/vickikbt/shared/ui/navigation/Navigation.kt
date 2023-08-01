package com.vickikbt.shared.ui.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import com.vickikbt.shared.presentation.ui.navigation.NavigationItem
import com.vickikbt.shared.presentation.ui.screens.favorites.FavoritesScreen
import com.vickikbt.shared.presentation.ui.screens.home.HomeScreen
import com.vickikbt.shared.presentation.ui.screens.settings.SettingsScreen
import com.vickikbt.shared.ui.screens.details.DetailsV2Screen
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.path

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Navigation(navigator: Navigator) {
    NavHost(navigator = navigator, initialRoute = NavigationItem.Home.route) {
        scene(NavigationItem.Home.route) {
            HomeScreen(navigator = navigator)
        }

        scene(NavigationItem.Favorites.route) {
            FavoritesScreen()
        }

        scene(NavigationItem.Settings.route) {
            SettingsScreen()
        }

        scene(NavigationItem.Details.route) { backStackEntry ->
            backStackEntry.path<Int>("id")?.let { movieId ->
                DetailsV2Screen(navigator = navigator, movieId = movieId)
            }
        }
    }
}

package com.vickikbt.shared.ui.navigation

import androidx.compose.runtime.Composable
import com.vickikbt.shared.presentation.ui.navigation.NavigationItem
import com.vickikbt.shared.ui.screens.details.DetailsScreen
import com.vickikbt.shared.ui.screens.favorites.FavoritesScreen
import com.vickikbt.shared.ui.screens.home.HomeScreen
import com.vickikbt.shared.ui.screens.search.SearchScreen
import com.vickikbt.shared.ui.screens.settings.SettingsScreen
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.path

@Composable
fun Navigation(navigator: Navigator) {
    NavHost(navigator = navigator, initialRoute = NavigationItem.Home.route) {
        scene(NavigationItem.Home.route) {
            HomeScreen(navigator = navigator)
        }

        scene(NavigationItem.Search.route) {
            SearchScreen()
        }

        scene(NavigationItem.Favorites.route) {
            FavoritesScreen()
        }

        scene(NavigationItem.Settings.route) {
            SettingsScreen()
        }

        scene(NavigationItem.Details.route) { backStackEntry ->
            backStackEntry.path<Int>("id")?.let { movieId ->
                DetailsScreen(navigator = navigator, movieId = movieId)
            }
        }
    }
}

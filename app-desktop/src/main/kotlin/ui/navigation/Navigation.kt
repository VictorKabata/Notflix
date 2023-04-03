package ui.navigation

import androidx.compose.runtime.Composable
import ui.screens.details.DetailsComposableScreen
import ui.screens.favourites.FavoritesComposableScreen
import ui.screens.home.HomeComposableScreen
import ui.screens.settings.SettingsComposableScreen
import utils.getNavArguments

@Composable
fun Navigation(navController: NavController) {
    NavHost(navController = navController) {
        composable(route = NavigationItem.Home.route) {
            HomeComposableScreen(navController = navController)
        }

        composable(route = NavigationItem.Details.route) {
            val movieId = it.getNavArguments().toInt()
            DetailsComposableScreen(navController = navController, movieId = movieId)
        }

        composable(route = NavigationItem.Favorites.route) {
            FavoritesComposableScreen(navController = navController)
        }

        composable(route = NavigationItem.Settings.route) {
            SettingsComposableScreen(navController = navController)
        }
    }.build()
}

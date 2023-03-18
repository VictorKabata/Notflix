package ui.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import ui.screens.details.DetailsScreen
import ui.screens.favourites.FavoritesScreen
import ui.screens.home.HomeScreen
import ui.screens.settings.SettingsScreen

@ExperimentalMaterialApi
@Composable
fun Navigation(navController: NavController) {
    NavHost(navController = navController) {
        composable(route = NavigationItem.Home.route) {
            HomeScreen(navController = navController)
        }

        composable(route = NavigationItem.Details.route, arguments = listOf()) {
            val movieId = it[0].toInt()
            DetailsScreen(navController = navController, movieId = movieId)
        }

        composable(route = NavigationItem.Favorites.route) {
            FavoritesScreen(navController = navController)
        }

        composable(route = NavigationItem.Settings.route) {
            SettingsScreen(navController = navController)
        }
    }.build()
}

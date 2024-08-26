package com.vickbt.shared.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.vickbt.shared.ui.screens.details.DetailsScreen
import com.vickbt.shared.ui.screens.favorites.FavoritesScreen
import com.vickbt.shared.ui.screens.home.HomeScreen
import com.vickbt.shared.ui.screens.search.SearchScreen
import com.vickbt.shared.ui.screens.settings.SettingsScreen
import com.vickbt.shared.utils.WindowSize
import io.github.aakira.napier.Napier

@Composable
fun Navigation(
    navHostController: NavHostController,
    windowSize: WindowSize,
    mainPaddingValues: PaddingValues = PaddingValues()
) {
    NavHost(navController = navHostController, startDestination = NavigationItem.Home.route) {
        composable(route = NavigationItem.Home.route) {
            HomeScreen(
                navigator = navHostController,
                windowSize = windowSize,
                mainPaddingValues = mainPaddingValues
            )
        }

        composable(route = NavigationItem.Favorites.route) {
            FavoritesScreen(navigator = navHostController, mainPaddingValues = mainPaddingValues)
        }

        composable(route = NavigationItem.Search.route) {
            SearchScreen(navigator = navHostController, mainPaddingValues = mainPaddingValues)
        }

        composable(route = NavigationItem.Settings.route) {
            SettingsScreen(mainPaddingValues = mainPaddingValues)
        }

        composable(
            route = NavigationItem.Details.route,
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getInt("movieId")?.let { movieId ->
                Napier.e("Movie ID: $movieId")
                DetailsScreen(
                    navigator = navHostController,
                    windowSize = windowSize,
                    movieId = movieId
                )
            }
        }
    }
}

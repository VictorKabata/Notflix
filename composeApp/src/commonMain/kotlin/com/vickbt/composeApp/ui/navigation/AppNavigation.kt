package com.vickbt.composeApp.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.navArgument
import androidx.window.core.layout.WindowSizeClass
import com.vickbt.composeApp.ui.screens.details.DetailsScreen
import com.vickbt.composeApp.ui.screens.favorites.FavoritesScreen
import com.vickbt.composeApp.ui.screens.home.HomeScreen
import com.vickbt.composeApp.ui.screens.search.SearchScreen
import com.vickbt.composeApp.ui.screens.settings.SettingsScreen
import com.vickbt.composeApp.utils.animatedComposable

@Composable
fun AppNavigation(
    navHostController: NavHostController,
    windowSize: WindowSizeClass,
    mainPaddingValues: PaddingValues = PaddingValues()
) {
    NavHost(navController = navHostController, startDestination = NavigationItem.Home.route) {
        animatedComposable(route = NavigationItem.Home.route) {
            HomeScreen {
                navHostController.navigate(it) {
                    popUpTo(NavigationItem.Home.route)
                }
            }
        }

        animatedComposable(route = NavigationItem.Favorites.route) {
            FavoritesScreen {
                navHostController.navigate(it) {
                    popUpTo(NavigationItem.Favorites.route)
                }
            }
        }

        animatedComposable(route = NavigationItem.Settings.route) {
            SettingsScreen()
        }

        animatedComposable(route = NavigationItem.Search.route) {
            SearchScreen { destination ->
                if (destination.isNullOrEmpty()) {
                    navHostController.navigateUp()
                } else {
                    navHostController.navigate(destination) {
                        popUpTo(NavigationItem.Home.route)
                    }
                }
            }
        }

        animatedComposable(
            route = NavigationItem.Details.route,
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getInt("movieId")?.let { movieId ->
                DetailsScreen(movieId = movieId) { destination ->
                    if (destination.isNullOrEmpty()) {
                        navHostController.navigateUp()
                    } else {
                        navHostController.navigate(destination)
                    }
                }
            }
        }
    }
}

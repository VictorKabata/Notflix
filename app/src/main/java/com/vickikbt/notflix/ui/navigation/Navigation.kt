package com.vickikbt.notflix.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.accompanist.pager.ExperimentalPagerApi
import com.vickikbt.notflix.ui.screens.details.DetailsScreen
import com.vickikbt.notflix.ui.screens.favorites.FavoritesScreen
import com.vickikbt.notflix.ui.screens.home.HomeScreen
import com.vickikbt.notflix.ui.screens.settings.SettingsScreen

@ExperimentalAnimationApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun Navigation(navController: NavHostController) {

    // val defaultEnterAnimationDuration = 600
    // val defaultExitAnimationDuration = 1100
    // val slideDefaultInitialOffset = 1800
    // val slideDefaultTargetOffset = 1500

    NavHost(navController = navController, startDestination = NavigationItem.Home.route) {
        composable(route = NavigationItem.Home.route) {
            HomeScreen(navController = navController)
        }

        composable(route = NavigationItem.Favorites.route) {
            FavoritesScreen(navController = navController)
        }

        composable(route = NavigationItem.Settings.route) {
            SettingsScreen(navController = navController)
        }

        composable(
            route = NavigationItem.Details.route,
            arguments = listOf(
                navArgument("movieId") {
                    type = NavType.IntType
                }
            )
        ) {
            val movieID = it.arguments?.getInt("movieId")
            if (movieID != null) {
                DetailsScreen(navController = navController, movieId = movieID)
            }
        }
    }
}

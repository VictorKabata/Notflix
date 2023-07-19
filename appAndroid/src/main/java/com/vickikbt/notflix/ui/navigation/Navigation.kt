package com.vickikbt.notflix.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.accompanist.pager.ExperimentalPagerApi
import com.vickikbt.shared.presentation.ui.navigation.NavigationItem
import com.vickikbt.shared.presentation.ui.screens.home.HomeScreen

@ExperimentalFoundationApi
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
            HomeScreen()
        }

        composable(route = NavigationItem.Favorites.route) {
            HomeScreen()
        }

        composable(route = NavigationItem.Settings.route) {
            HomeScreen()
        }

        composable(
            route = NavigationItem.Details.route,
            arguments = listOf(
                navArgument("movieId") {
                    type = NavType.IntType
                },
                navArgument("cacheId") {
                    type = NavType.IntType
                }
            )
        ) {
            val movieID = it.arguments?.getInt("movieId")
            val cacheId = it.arguments?.getInt("cacheId")
            if (movieID != null && cacheId != null) {
                HomeScreen()
            }
        }
    }
}

package com.vickikbt.notflix.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.vickikbt.notflix.ui.screens.details.DetailsScreen
import com.vickikbt.notflix.ui.screens.favorites.FavoritesScreen
import com.vickikbt.notflix.ui.screens.home.HomeScreen
import com.vickikbt.notflix.ui.screens.settings.SettingsScreen

@ExperimentalAnimationApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun Navigation(navController: NavHostController) {

    val defaultEnterAnimationDuration = 600
    val defaultExitAnimationDuration = 1100
    val slideDefaultInitialOffset = 1800
    val slideDefaultTargetOffset = 1500

    AnimatedNavHost(navController = navController, startDestination = NavigationItem.Home.route){
        composable(route=NavigationItem.Home.route){
            HomeScreen(navController = navController)
        }

        composable(route=NavigationItem.Favorites.route){
            FavoritesScreen(navController = navController)
        }

        composable(route=NavigationItem.Settings.route){
            SettingsScreen(navController = navController)
        }

        composable(route=NavigationItem.Details.route){
            DetailsScreen(navController = navController)
        }
    }

}
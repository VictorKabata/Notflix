package com.vickikbt.shared.presentation.ui.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import com.vickikbt.shared.presentation.ui.screens.details.DetailsScreen
import com.vickikbt.shared.presentation.ui.screens.favorites.FavoritesScreen
import com.vickikbt.shared.presentation.ui.screens.home.HomeScreen
import com.vickikbt.shared.presentation.ui.screens.settings.SettingsScreen
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Navigation(navigator: Navigator) {
    NavHost(navigator = navigator, initialRoute = NavigationItem.Home.route) {

        scene(NavigationItem.Home.route) {
            HomeScreen()
        }

        scene(NavigationItem.Favorites.route) {
            FavoritesScreen()
        }

        scene(NavigationItem.Settings.route) {
            SettingsScreen()
        }

        scene(NavigationItem.Details.route) {
            DetailsScreen()
        }

    }
}

package com.vickbt.shared.ui.screens.main

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.vickbt.shared.ui.components.BottomNavBar
import com.vickbt.shared.ui.components.NavRailBar
import com.vickbt.shared.ui.navigation.Navigation
import com.vickbt.shared.ui.navigation.NavigationItem
import com.vickbt.shared.ui.theme.NotflixTheme
import com.vickbt.shared.utils.WindowSize
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainScreen(viewModel: MainViewModel = koinViewModel<MainViewModel>()) {
    val appUiState = viewModel.mainUiState.collectAsState().value
    var windowSize by remember { mutableStateOf(WindowSize.COMPACT) }

    val isDarkTheme = appUiState.selectedTheme != 0

    NotflixTheme(darkTheme = isDarkTheme) {
        val navHostController = rememberNavController()

        val topLevelDestinations = listOf(
            NavigationItem.Home,
            NavigationItem.Favorites,
            NavigationItem.Settings
        )

        val isTopLevelDestination =
            navHostController.currentBackStackEntryAsState().value?.destination?.route in topLevelDestinations.map { it.route }

        val showNavigationRail = windowSize != WindowSize.COMPACT

        Scaffold(
            bottomBar = {
                if (isTopLevelDestination && !showNavigationRail) {
                    BottomNavBar(
                        bottomNavItems = topLevelDestinations,
                        navHostController = navHostController
                    )
                }
            }
        ) { paddingValues ->

            BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
                windowSize = WindowSize.basedOnWidth(this.minWidth)

                Row(modifier = Modifier.fillMaxSize()) {
                    if (isTopLevelDestination && showNavigationRail) {
                        NavRailBar(
                            navigationItems = topLevelDestinations,
                            navHostController = navHostController
                        )
                    }

                    Navigation(
                        navHostController = navHostController,
                        windowSize = windowSize,
                        paddingValues = paddingValues
                    )
                }
            }
        }
    }
}

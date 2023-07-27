package com.vickikbt.shared.presentation.ui.screens.main

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.vickikbt.shared.presentation.ui.components.BottomNavBar
import com.vickikbt.shared.presentation.ui.navigation.Navigation
import com.vickikbt.shared.presentation.ui.navigation.NavigationItem
import com.vickikbt.shared.presentation.ui.theme.NotflixTheme
import moe.tlaster.precompose.navigation.rememberNavigator
import org.koin.compose.koinInject

@Composable
fun MainScreen(viewModel: MainViewModel = koinInject()) {

    val appUiState = viewModel.mainUiState.collectAsState().value

    val isDarkTheme = appUiState.selectedTheme != 0

    NotflixTheme(darkTheme = isDarkTheme) {
        Surface(color = MaterialTheme.colors.surface) {

            val navigator = rememberNavigator()

            val topLevelDestinations = listOf(
                NavigationItem.Home,
                NavigationItem.Favorites,
                NavigationItem.Settings
            )

            val isTopLevelDestination =
                navigator.currentEntry.collectAsState(null).value?.route?.route in topLevelDestinations.map { it.route }

            Scaffold(
                bottomBar = {
                    if (isTopLevelDestination) {
                        BottomNavBar(bottomNavItems = topLevelDestinations, navigator = navigator)
                    }
                }
            ) {
                Navigation(navigator = navigator)
            }
        }
    }
}

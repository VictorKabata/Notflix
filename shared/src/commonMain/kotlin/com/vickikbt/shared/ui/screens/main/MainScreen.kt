package com.vickikbt.shared.ui.screens.main

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.vickikbt.shared.ui.components.BottomNavBar
import com.vickikbt.shared.presentation.ui.navigation.NavigationItem
import com.vickikbt.shared.presentation.ui.screens.main.MainViewModel
import com.vickikbt.shared.ui.navigation.Navigation
import com.vickikbt.shared.ui.theme.NotflixTheme
import moe.tlaster.precompose.navigation.rememberNavigator
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MainViewModel = koinInject()) {

    val appUiState = viewModel.mainUiState.collectAsState().value

    val isDarkTheme = appUiState.selectedTheme != 0

    NotflixTheme(darkTheme = isDarkTheme) {
        Surface(color = MaterialTheme.colorScheme.surface) {

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

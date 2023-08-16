package com.vickikbt.shared.ui.screens.main

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.vickikbt.shared.presentation.ui.navigation.NavigationItem
import com.vickikbt.shared.presentation.ui.screens.main.MainViewModel
import com.vickikbt.shared.ui.components.BottomNavBar
import com.vickikbt.shared.ui.components.NavRailBar
import com.vickikbt.shared.ui.navigation.Navigation
import com.vickikbt.shared.ui.theme.NotflixTheme
import com.vickikbt.shared.utils.WindowSize
import moe.tlaster.precompose.navigation.rememberNavigator
import org.koin.compose.koinInject

@Composable
fun MainScreen(viewModel: MainViewModel = koinInject()) {

    val appUiState = viewModel.mainUiState.collectAsState().value
    var windowSize by remember { mutableStateOf(WindowSize.COMPACT) }

    val isDarkTheme = appUiState.selectedTheme != 0

    NotflixTheme(darkTheme = isDarkTheme) {

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
                    if (windowSize == WindowSize.COMPACT) {
                        BottomNavBar(bottomNavItems = topLevelDestinations, navigator = navigator)
                    } else {
                        NavRailBar(navigationItems = topLevelDestinations, navigator = navigator)
                    }
                }
            }
        ) { paddingValues ->
            Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.surface) {
                BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
                    windowSize = WindowSize.basedOnWidth(this.minWidth)

                    Navigation(navigator = navigator, windowSize = windowSize, paddingValues=paddingValues)
                }
            }
        }
    }
}

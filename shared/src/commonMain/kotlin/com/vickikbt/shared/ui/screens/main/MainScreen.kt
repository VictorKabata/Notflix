package com.vickikbt.shared.ui.screens.main

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.vickikbt.shared.presentation.ui.navigation.NavigationItem
import com.vickikbt.shared.presentation.ui.screens.main.MainViewModel
import com.vickikbt.shared.ui.components.BottomNavBar
import com.vickikbt.shared.ui.navigation.Navigation
import com.vickikbt.shared.ui.theme.LocalWindowSize
import com.vickikbt.shared.ui.theme.NotflixTheme
import com.vickikbt.shared.utils.WindowSize
import io.github.aakira.napier.Napier
import moe.tlaster.precompose.navigation.rememberNavigator
import org.koin.compose.koinInject

@Composable
fun MainScreen(viewModel: MainViewModel = koinInject()) {

    val appUiState = viewModel.mainUiState.collectAsState().value

    val windowSize = LocalWindowSize.current
    val isDarkTheme = appUiState.selectedTheme != 0

    NotflixTheme(windowSize = windowSize, darkTheme = isDarkTheme) {

        val navigator = rememberNavigator()

        val topLevelDestinations = listOf(
            NavigationItem.Home,
            NavigationItem.Favorites,
            NavigationItem.Settings
        )

        val isTopLevelDestination =
            navigator.currentEntry.collectAsState(null).value?.route?.route in topLevelDestinations.map { it.route }

        Napier.e(tag = "VicKbt", message = "isTopLevel: $isTopLevelDestination")
        Napier.e(tag = "VicKbt", message = "Window size: $windowSize")



        Scaffold(
            bottomBar = {
                if (isTopLevelDestination) {
                    BottomNavBar(bottomNavItems = topLevelDestinations, navigator = navigator)
                }
            }
        ) {
            Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.surface) {
                BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
                    val some = WindowSize.basedOnWidth(this.minWidth)
                    Napier.e(tag = "VicKbt", message = "New Window size: $some")

                    Navigation(navigator = navigator)
                }
            }
        }
    }
}

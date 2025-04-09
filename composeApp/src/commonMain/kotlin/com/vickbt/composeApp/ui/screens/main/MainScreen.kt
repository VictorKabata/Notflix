package com.vickbt.composeApp.ui.screens.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.window.core.layout.WindowWidthSizeClass
import com.vickbt.composeApp.ui.navigation.AppNavigation
import com.vickbt.composeApp.ui.navigation.NavigationItem
import com.vickbt.composeApp.ui.theme.NotflixTheme
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainScreen(viewModel: MainViewModel = koinViewModel<MainViewModel>()) {
    val windowSize = currentWindowAdaptiveInfo().windowSizeClass

    val appUiState = viewModel.mainUiState.collectAsState().value

    val isDarkTheme = appUiState.selectedTheme != 0

    NotflixTheme(darkTheme = isDarkTheme) {
        val navHostController = rememberNavController()

        val topLevelDestinations = listOf(
            NavigationItem.Home,
            NavigationItem.Favorites,
            NavigationItem.Settings
        )

        val currentDestination by rememberSaveable { mutableStateOf(navHostController.currentDestination?.route) }
        val isTopLevelDestination =
            navHostController.currentBackStackEntryAsState().value?.destination?.route in topLevelDestinations.map { it.route }

        val navigationLayout = if (!isTopLevelDestination) {
            NavigationSuiteType.None
        } else if (windowSize.windowWidthSizeClass == WindowWidthSizeClass.COMPACT) {
            NavigationSuiteType.NavigationBar
        } else {
            NavigationSuiteType.NavigationRail
        }

        NavigationSuiteScaffold(
            modifier = Modifier.fillMaxSize(),
            navigationSuiteItems = {
                topLevelDestinations.iterator().forEach { destination ->
                    item(
                        icon = {
                            destination.icon?.let {
                                Icon(
                                    modifier = Modifier,
                                    imageVector = destination.icon,
                                    contentDescription = stringResource(destination.title)
                                )
                            }
                        },
                        label = {
                            Text(
                                text = stringResource(destination.title),
                                style = MaterialTheme.typography.labelSmall,
                                maxLines = 1
                            )
                        },
                        selected = destination.route == currentDestination,
                        onClick = {
                            navHostController.navigate(destination.route) {
                                popUpTo(NavigationItem.Home.route)
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            },
            containerColor = MaterialTheme.colorScheme.background.copy(alpha = .85f),
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            layoutType = navigationLayout
        ) {
            AppNavigation(navHostController = navHostController, windowSize = windowSize)
        }
    }
}

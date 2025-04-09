package com.vickbt.composeApp.utils

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.vickbt.composeApp.ui.navigation.NavigationItem

fun NavGraphBuilder.animatedComposable(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = route,
        arguments = arguments,
        enterTransition = {
            val initialIndex = this.initialState.destination.screenIndex()
            val targetIndex = this.targetState.destination.screenIndex()
            if (targetIndex > initialIndex) {
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(300)
                )
            } else if (targetIndex < initialIndex) {
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> -fullWidth },
                    animationSpec = tween(300)
                )
            } else {
                null
            }
        },
        exitTransition = {
            val initialIndex = this.initialState.destination.screenIndex()
            val targetIndex = this.targetState.destination.screenIndex()

            if (targetIndex > initialIndex) {
                slideOutHorizontally(
                    targetOffsetX = { fullWidth -> -fullWidth },
                    animationSpec = tween(300)
                )
            } else if (targetIndex < initialIndex) {
                slideOutHorizontally(
                    targetOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(300)
                )
            } else {
                null
            }
        }
    ) { backStackEntry ->
        content(backStackEntry)
    }
}

private val screenOrder = listOf(
    NavigationItem.Home.route,
    NavigationItem.Favorites.route,
    NavigationItem.Settings.route
)

private fun NavDestination.screenIndex(): Int {
    return screenOrder.indexOf(this.route)
}

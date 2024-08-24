package com.vickbt.shared.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(
    val route: String,
    val title: String,
    val icon: ImageVector?
) {

    object Home : NavigationItem("/home", "Home", Icons.Rounded.Home)
    object Favorites : NavigationItem("/favorites", "Favorites", Icons.Rounded.Favorite)
    object Search : NavigationItem("/search", "Search", Icons.Rounded.Search)
    object Settings : NavigationItem("/settings", "Settings", Icons.Rounded.Settings)
    object Details : NavigationItem("/details/{movieId}", "Details", null)
}

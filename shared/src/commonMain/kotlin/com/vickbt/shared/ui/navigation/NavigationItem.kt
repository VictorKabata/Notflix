package com.vickbt.shared.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.vickbt.notflix.MR
import dev.icerock.moko.resources.StringResource

sealed class NavigationItem(
    val route: String,
    val title: StringResource,
    val icon: ImageVector?
) {
    object Home : NavigationItem("/home", MR.strings.home, Icons.Rounded.Home)
    object Favorites : NavigationItem("/favorites", MR.strings.favorites, Icons.Rounded.Favorite)
    object Settings : NavigationItem("/settings", MR.strings.settings, Icons.Rounded.Settings)
    object Details : NavigationItem("/details/{id:[0-9]+}", MR.strings.details, null)
}

package com.vickbt.shared.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import notflix.shared.generated.resources.Res
import notflix.shared.generated.resources.title_details
import notflix.shared.generated.resources.title_favorites
import notflix.shared.generated.resources.title_home
import notflix.shared.generated.resources.title_settings
import org.jetbrains.compose.resources.StringResource

sealed class NavigationItem(
    val route: String,
    val title: StringResource,
    val icon: ImageVector?
) {

    data object Home : NavigationItem("/home", Res.string.title_home, Icons.Rounded.Home)
    data object Favorites :
        NavigationItem("/favorites", Res.string.title_favorites, Icons.Rounded.Favorite)

    data object Settings :
        NavigationItem("/settings", Res.string.title_settings, Icons.Rounded.Settings)

    data object Details : NavigationItem("/details/{movieId}", Res.string.title_details, null)
}

package com.vickbt.shared.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import notflix.shared.generated.resources.Res
import notflix.shared.generated.resources.title_details
import notflix.shared.generated.resources.title_favorites
import notflix.shared.generated.resources.title_home
import notflix.shared.generated.resources.title_search
import notflix.shared.generated.resources.title_settings
import org.jetbrains.compose.resources.StringResource

sealed class NavigationItem(
    val route: String,
    val title: StringResource,
    val icon: ImageVector?
) {

    object Home : NavigationItem("/home", Res.string.title_home, Icons.Rounded.Home)
    object Favorites :
        NavigationItem("/favorites", Res.string.title_favorites, Icons.Rounded.Favorite)

    object Search :
        NavigationItem("/search", Res.string.title_search, Icons.Rounded.Search)

    object Settings : NavigationItem("/settings", Res.string.title_settings, Icons.Rounded.Settings)
    object Details : NavigationItem("/details/{movieId}", Res.string.title_details, null)
}

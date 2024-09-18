package com.vickbt.composeApp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.vickbt.shared.resources.Res
import com.vickbt.shared.resources.title_favorites
import com.vickbt.shared.resources.title_home
import com.vickbt.shared.resources.title_search
import com.vickbt.shared.resources.title_settings
import com.vickbt.shared.resources.title_details
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

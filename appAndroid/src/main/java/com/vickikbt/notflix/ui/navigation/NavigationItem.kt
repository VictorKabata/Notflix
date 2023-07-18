package com.vickikbt.notflix.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.vickikbt.notflix.R
import com.vickikbt.notflix.ui.navigation.NavigationItem

sealed class NavigationItem(
    val route: String,
    @StringRes val title: Int,
    @DrawableRes val icon: Int?
) {

    object Home : NavigationItem("home", R.string.title_home, R.drawable.ic_home)
    object Favorites : NavigationItem("favorites", R.string.title_favorites, R.drawable.ic_favourite)
    object Settings : NavigationItem("settings", R.string.title_settings, R.drawable.ic_settings)
    object Details : NavigationItem("details/{movieId}/{cacheId}", R.string.title_details, null)
}

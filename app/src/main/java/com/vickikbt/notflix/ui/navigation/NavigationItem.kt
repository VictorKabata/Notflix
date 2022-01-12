package com.vickikbt.notflix.ui.navigation

import com.vickikbt.notflix.R

sealed class NavigationItem(var route: String, var title: String, var icon: Int?) {

    object Home : NavigationItem("home", "Home", R.drawable.ic_home)
    object Favorites : NavigationItem("favorites", "Favorites", R.drawable.ic_favourite)
    object Settings : NavigationItem("settings", "Settings", R.drawable.ic_settings)

    object Details : NavigationItem("details", "Movie Details", null)
}

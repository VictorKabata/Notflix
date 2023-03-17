package ui.navigation

sealed class NavigationItem(
    val route: String,
    val title: String,
    val icon: String?
) {

    object Home : NavigationItem("home", "Home", "ic_home.svg")
    object Favorites : NavigationItem("favorites", "Favourites", "ic_favourite.svg")
    object Settings : NavigationItem("settings", "Settings", "ic_settings.svg")
    object Details : NavigationItem("details/{movieId}", "Movie Details", null)
    object Splash : NavigationItem("splash", "", null)
}

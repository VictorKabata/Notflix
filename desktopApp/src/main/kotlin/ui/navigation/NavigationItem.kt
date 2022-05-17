package ui.navigation

sealed class NavigationItem(
    val route: String,
    val title: String,
    val icon: Int?
) {

    object Home : NavigationItem("home", "Home", null)
    object Favorites : NavigationItem("favorites", "Favourites", null)
    object Settings : NavigationItem("settings", "Settings", null)
    object Details : NavigationItem("details/{movieId}/{cacheId}", "Movie Details", null)
    object Splash : NavigationItem("splash", "", null)
}

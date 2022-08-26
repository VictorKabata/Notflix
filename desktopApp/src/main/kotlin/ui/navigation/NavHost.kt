package ui.navigation

import androidx.compose.runtime.Composable
import io.github.aakira.napier.Napier

/**
 * NavigationHost class
 */
class NavHost(
    val navController: NavController,
    val contents: @Composable NavigationGraphBuilder.() -> Unit
) {

    @Composable
    fun build() {
        NavigationGraphBuilder().renderContents()
    }

    inner class NavigationGraphBuilder(
        val navController: NavController = this@NavHost.navController
    ) {
        @Composable
        fun renderContents() {
            this@NavHost.contents(this)
        }
    }
}

/**
 * Composable to build the Navigation Host
 */
@Composable
fun NavHost.NavigationGraphBuilder.composable(
    route: String,
    arguments: List<String> = emptyList(),
    content: @Composable() (arguments: List<String>) -> Unit
) {
    Napier.e("Navigating to: $route")
    if (navController.currentDestination.value == route) {
        content(arguments)
    }
}

package ui.screens.favourites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import ui.navigation.NavController

class FavouriteScreen : Screen {

    @Composable
    override fun Content() {
        FavoritesComposableScreen()
    }

}

@Composable
fun FavoritesComposableScreen(navController: NavController? = null) {
    Box(modifier = Modifier.fillMaxSize())
}

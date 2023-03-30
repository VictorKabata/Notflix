package ui.screens.favourites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen

class FavouriteScreen : Screen {

    @Composable
    override fun Content() {
        FavoritesComposableScreen()
    }

}

@Composable
fun FavoritesComposableScreen() {
    Box(modifier = Modifier.fillMaxSize())
}

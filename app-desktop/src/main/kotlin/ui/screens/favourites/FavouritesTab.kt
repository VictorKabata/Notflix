package ui.screens.favourites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions

object FavouritesTab : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val title by remember { mutableStateOf("Favourites") }
            val icon = painterResource("ic_favourite.svg")

            return remember {
                TabOptions(
                    index = 1u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        FavoritesComposableScreen()
    }
}

@Composable
fun FavoritesComposableScreen() {
    Box(modifier = Modifier.fillMaxSize())
}

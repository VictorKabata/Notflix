package ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ui.components.ItemNowPlayingMovies

@Composable
fun HomeScreen() {
    Column(modifier = Modifier.fillMaxSize()) {

        ItemNowPlayingMovies(modifier = Modifier, movie = FakeMovie()) {
            println("Clicked movie: ${it.title}")
        }

    }
}

data class FakeMovie(
    val posterPath: String = "https://image.tmdb.org/t/p/original/AdyJH8kDm8xT8IKTlgpEC15ny4u.jpg",
    val title: String = "Dr. Strange and the Multiverse of Madness"
)

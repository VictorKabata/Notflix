package ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.vickikbt.shared.domain.models.Movie
import com.vickikbt.shared.presentation.viewmodels.SharedHomeViewModel
import koin
import ui.components.ItemNowPlayingMovies

@Composable
fun HomeScreen() {

    val viewModel = koin.get<SharedHomeViewModel>()
    val nowPlayingMovies = viewModel.nowPlayingMovies.collectAsState()

    val scrollState = rememberScrollState()

    Column(modifier = Modifier.fillMaxSize()) {
        nowPlayingMovies.value?.let { NowPlayingMovies(movies = it) }
    }
}

@Composable
fun NowPlayingMovies(movies: List<Movie>) {
    LazyRow(modifier = Modifier.fillMaxWidth().wrapContentHeight()) {
        items(items = movies) { items ->

            ItemNowPlayingMovies(
                modifier = Modifier
                    .fillMaxWidth(),
                movie = items
            ) {
                println("Clicked movie: ${it.title}")
            }
        }
    }
}

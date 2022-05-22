package ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vickikbt.shared.domain.models.Movie
import com.vickikbt.shared.presentation.viewmodels.SharedHomeViewModel
import koin
import ui.components.ItemNowPlayingMovies
import ui.components.ItemPopularMovies
import ui.components.ItemTrendingMovies
import ui.components.SectionSeparator

@Composable
fun HomeScreen() {

    val viewModel = koin.get<SharedHomeViewModel>()

    val nowPlayingMovies = viewModel.nowPlayingMovies.collectAsState()
    val trendingMovies = viewModel.trendingMovies.collectAsState()
    val popularMovies = viewModel.popularMovies.collectAsState()
    val upcomingMovies = viewModel.upcomingMovies.collectAsState()

    val parentScrollState = rememberScrollState(0)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = parentScrollState)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (!nowPlayingMovies.value.isNullOrEmpty()) NowPlayingMovies(movies = nowPlayingMovies.value!!)
            trendingMovies.value?.let { TrendingMovies(movies = it) }
            popularMovies.value?.let { PopularMovies(movies = it) }
            upcomingMovies.value?.let { UpcomingMovies(movies = it) }
        }
    }
}

@Composable
fun NowPlayingMovies(movies: List<Movie>) {
    val movieNumber by remember { mutableStateOf((0..movies.size).random()) }

    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            ItemNowPlayingMovies(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(450.dp),
                movie = movies[0]
            ) {
                println("Clicked movie: ${it.title}")
            }
        }
    }
}

@Composable
fun TrendingMovies(movies: List<Movie>) {
    SectionSeparator(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 12.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        sectionTitle = "Trending Movies",
        onItemClick = {
            // ToDo: OnSectionedClicked-navigate to view all
        }
    )

    Spacer(modifier = Modifier.height(2.dp))

    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(items = movies) { item ->
            ItemTrendingMovies(modifier = Modifier, movie = item) {
                // ToDo: On movie clicked
            }
        }
    }
}

@Composable
fun PopularMovies(movies: List<Movie>) {
    SectionSeparator(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 12.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        sectionTitle = "Popular Movies",
        onItemClick = {
            // ToDo: OnSectionedClicked-navigate to view all
        }
    )

    Spacer(modifier = Modifier.height(2.dp))

    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        items(items = movies) { item ->
            ItemPopularMovies(
                modifier = Modifier
                    .width(480.dp)
                    .height(346.dp),
                movie = item
            ) {
                // ToDo: On movie clicked
            }
        }
    }
}

@Composable
fun UpcomingMovies(movies: List<Movie>) {
    SectionSeparator(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 12.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        sectionTitle = "Popular Movies",
        onItemClick = {
            // ToDo: OnSectionedClicked-navigate to view all
        }
    )

    Spacer(modifier = Modifier.height(2.dp))

    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(items = movies) { item ->
            ItemTrendingMovies(modifier = Modifier, movie = item) {
                // ToDo: On movie clicked
            }
        }
    }
}

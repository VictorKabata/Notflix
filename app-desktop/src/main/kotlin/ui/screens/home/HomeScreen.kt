package ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.vickikbt.shared.domain.models.Movie
import koin
import ui.components.ItemNowPlayingMovies
import ui.components.ItemPopularMovies
import ui.components.ItemTrendingMovies
import ui.components.SectionSeparator
import ui.screens.details.DetailsScreen

class HomeScreen : Screen {
    @Composable
    override fun Content() {
        HomeComposeScreen()
    }
}

@Composable
fun HomeComposeScreen(viewModel: HomeScreenModel = koin.get()) {

    val navigator = LocalNavigator.currentOrThrow

    LaunchedEffect(key1 = viewModel) {
        viewModel.fetchNowPlayingMovies()
        viewModel.fetchUpcomingMovies()
        viewModel.fetchPopularMovies()
        viewModel.fetchTrendingMovies()
    }

    val homeUiState = viewModel.homeUiState.collectAsState().value

    val nowPlayingMovies = homeUiState.nowPlayingMovies
    val trendingMovies = homeUiState.trendingMovies
    val popularMovies = homeUiState.popularMovies
    val upcomingMovies = homeUiState.upcomingMovies

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
            nowPlayingMovies?.let { NowPlayingMovies(navigator = navigator, movies = it) }
            trendingMovies?.let { TrendingMovies(navigator = navigator, movies = it) }
            popularMovies?.let { PopularMovies(navigator = navigator, movies = it) }
            upcomingMovies?.let { UpcomingMovies(navigator = navigator, movies = it) }
        }
    }
}

@Composable
fun NowPlayingMovies(navigator: Navigator, movies: List<Movie>) {
    Row(modifier = Modifier.fillMaxWidth().height(600.dp)) {
        for (movie in movies.take(5)) {
            Box(Modifier.weight(1f)) {
                ItemNowPlayingMovies(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(300.dp),
                    movie = movie
                ) { movie ->
                    movie.id?.let { navigator.push(DetailsScreen(movieId = it)) }
                }
            }
        }
    }
}

@Composable
fun TrendingMovies(navigator: Navigator, movies: List<Movie>) {
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
            ItemTrendingMovies(modifier = Modifier, movie = item) { movie ->
                movie.id?.let { navigator.push(DetailsScreen(movieId = it)) }
            }
        }
    }
}

@Composable
fun PopularMovies(navigator: Navigator, movies: List<Movie>) {
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
                modifier = Modifier,
                movie = item
            ) { movie ->
                movie.id?.let { navigator.push(DetailsScreen(movieId = it)) }
            }
        }
    }
}

@Composable
fun UpcomingMovies(navigator: Navigator, movies: List<Movie>) {
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
            ItemTrendingMovies(modifier = Modifier, movie = item) { movie ->
                movie.id?.let { navigator.push(DetailsScreen(movieId = it)) }
            }
        }
    }
}

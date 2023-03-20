@file:OptIn(ExperimentalComposeUiApi::class)

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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vickikbt.shared.domain.models.Movie
import koin
import ui.components.ItemNowPlayingMovies
import ui.components.ItemPopularMovies
import ui.components.ItemTrendingMovies
import ui.components.SectionSeparator
import ui.navigation.NavController

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = koin.get()
) {

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
            if (!nowPlayingMovies.value.isNullOrEmpty()) NowPlayingMovies(
                navController = navController,
                movies = nowPlayingMovies.value!!
            )
            trendingMovies.value?.let { TrendingMovies(navController = navController, movies = it) }
            popularMovies.value?.let { PopularMovies(navController = navController, movies = it) }
            upcomingMovies.value?.let { UpcomingMovies(navController = navController, movies = it) }
        }
    }
}

@Composable
fun NowPlayingMovies(navController: NavController, movies: List<Movie>) {
    Row(modifier = Modifier.fillMaxWidth().height(600.dp)) {
        for (movie in movies.take(5)) {
            Box(Modifier.weight(1f)) {
                ItemNowPlayingMovies(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(300.dp),
                    movie = movie
                ) {
                    println("Clicked movie: ${it.title}")
                }
            }
        }
    }
}

@Composable
fun TrendingMovies(navController: NavController, movies: List<Movie>) {
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
                navController.navigate("details/${it.id}")
            }
        }
    }
}

@Composable
fun PopularMovies(navController: NavController, movies: List<Movie>) {
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
            ) {
                navController.navigate("details/${it.id}")
            }
        }
    }
}

@Composable
fun UpcomingMovies(navController: NavController, movies: List<Movie>) {
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
                navController.navigate("details/${it.id}")
            }
        }
    }
}

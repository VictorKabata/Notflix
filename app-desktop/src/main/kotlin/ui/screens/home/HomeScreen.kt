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
import koin
import ui.components.ItemNowPlayingMovies
import ui.components.ItemPopularMovies
import ui.components.ItemTrendingMovies
import ui.components.SectionSeparator
import ui.navigation.NavController

@Composable
fun HomeComposeScreen(
    navController: NavController,
    viewModel: HomeScreenModel = koin.get()
) {

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

            //region Now Playing Movies
            nowPlayingMovies?.let {
                Row(modifier = Modifier.fillMaxWidth().height(600.dp)) {
                    for (movie in it.take(5)) {
                        Box(Modifier.weight(1f)) {
                            ItemNowPlayingMovies(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(300.dp),
                                movie = movie
                            ) { movie ->
                                movie.id?.let { navController.navigate("details/$it") }
                            }
                        }
                    }
                }
            }
            //endregion

            //region Trending Movies
            trendingMovies?.let {
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
                    items(items = it) { item ->
                        ItemTrendingMovies(modifier = Modifier, movie = item) { movie ->
                            movie.id?.let { navController.navigate("details/$it") }
                        }
                    }
                }
            }
            //endregion

            //region Popular Movies
            popularMovies?.let {
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
                    items(items = it) { item ->
                        ItemPopularMovies(
                            modifier = Modifier,
                            movie = item
                        ) { movie ->
                            movie.id?.let { navController.navigate("details/$it") }
                        }
                    }
                }
            }
            //endregion

            //region Upcoming Movies
            upcomingMovies?.let {
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
                    items(items = it) { item ->
                        ItemTrendingMovies(modifier = Modifier, movie = item) { movie ->
                            movie.id?.let { navController.navigate("details/$it") }
                        }
                    }
                }
            }
            //endregion
        }
    }
}

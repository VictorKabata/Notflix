package com.vickikbt.notflix.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.vickikbt.domain.models.Movie
import com.vickikbt.notflix.ui.components.ItemNowPlayingMovies
import com.vickikbt.notflix.ui.theme.DarkPrimaryColor
import com.vickikbt.notflix.ui.theme.Grey
import org.koin.androidx.compose.getViewModel
import timber.log.Timber

@ExperimentalPagerApi
@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = getViewModel()) {

    val scrollState = rememberScrollState()

    val nowPlayingMovies = viewModel.nowPlayingMovies.observeAsState().value

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.surface) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if (nowPlayingMovies != null) {
                NowPlayingMovies(
                    navController = navController,
                    viewModel = viewModel,
                    movies = nowPlayingMovies
                )
            }

        }

    }

}

@ExperimentalPagerApi
@Composable
fun NowPlayingMovies(
    navController: NavController,
    viewModel: HomeViewModel,
    movies: List<Movie>
) {
    val pagerState = rememberPagerState()

    Timber.e("Now playing: $movies")

    HorizontalPager(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        count = if (movies.size >= 5) 5 else movies.size,
        state = pagerState
    ) { page ->
        ItemNowPlayingMovies(
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp),
            viewModel = viewModel,
            movie = movies[page]
        ) {
            //ToDo: OnItemClick- Navigate to movie details
        }
    }

    Spacer(modifier = Modifier.height(6.dp))

    HorizontalPagerIndicator(
        modifier = Modifier.padding(vertical = 4.dp),
        pagerState = pagerState,
        indicatorHeight = 6.dp,
        indicatorWidth = 6.dp,
        spacing = 6.dp,
        activeColor = DarkPrimaryColor,
        inactiveColor = Grey
    )
}

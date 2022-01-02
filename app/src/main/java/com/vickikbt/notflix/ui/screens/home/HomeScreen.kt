package com.vickikbt.notflix.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.vickikbt.domain.models.Movie
import com.vickikbt.notflix.ui.components.ItemNowPlayingMovies
import org.koin.androidx.compose.getViewModel

@ExperimentalPagerApi
@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = getViewModel()) {

    /*val scrollState = rememberScrollState()

    val nowPlayingMovies = viewModel.nowPlayingMovies.observeAsState().value

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.surface) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {

            *//*if (nowPlayingMovies != null) {
                NowPlayingMovies(
                    navController = navController,
                    viewModel = viewModel,
                    movies = nowPlayingMovies
                )
            }*//*

        }

    }*/

}

@ExperimentalPagerApi
@Composable
fun NowPlayingMovies(navController: NavController, viewModel: HomeViewModel, movies: List<Movie>) {
    /*val pagerState = rememberPagerState()

    HorizontalPager(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        count = if (movies.size >= 5) 5 else movies.size,
        state = pagerState
    ) { page ->
        ItemNowPlayingMovies(viewModel = viewModel, movie = movies[page]) {
            //ToDo: OnItemClick- Navigate to movie details
        }
    }

    HorizontalPagerIndicator(pagerState = pagerState)*/
}

package com.vickikbt.notflix.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.vickikbt.domain.models.Movie
import com.vickikbt.notflix.R
import com.vickikbt.notflix.ui.components.ItemNowPlayingMovies
import com.vickikbt.notflix.ui.components.ItemPopularMovies
import com.vickikbt.notflix.ui.components.ItemRecentlyPlayedAlbum
import com.vickikbt.notflix.ui.components.SectionSeparator
import com.vickikbt.notflix.ui.theme.DarkPrimaryColor
import com.vickikbt.notflix.ui.theme.Gray
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.getViewModel
import timber.log.Timber

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = getViewModel()) {

    val scrollState = rememberScrollState()

    val nowPlayingMovies = viewModel.nowPlayingMovies.observeAsState().value
    val trendingMovies = viewModel.trendingMovies.observeAsState().value
    val popularMovies = viewModel.popularMovies.observeAsState().value
    val upcomingMovies = viewModel.upcomingMovies.observeAsState().value

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

            if (trendingMovies != null) {
                TrendingMovies(
                    navController = navController,
                    movies = trendingMovies
                )
            }

            if (popularMovies != null) {
                PopularMovies(
                    navController = navController,
                    viewModel = viewModel,
                    movies = popularMovies
                )
            }

            if (upcomingMovies != null) {
                UpcomingMovies(
                    navController = navController,
                    movies = upcomingMovies
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
        inactiveColor = Gray
    )
}

@Composable
fun TrendingMovies(navController: NavController, movies: Flow<PagingData<Movie>>) {

    val moviesList = movies.collectAsLazyPagingItems()

    SectionSeparator(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 12.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        sectionTitle = stringResource(id = R.string.trending_movies),
        onItemClick = {
            //ToDo: OnSectionedClicked-navigate to view all
        }
    )

    Spacer(modifier = Modifier.height(8.dp))

    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(items = moviesList) { item ->
            ItemRecentlyPlayedAlbum(movie = item!!, onItemClick = {
                //ToDo: OnItemClicked-Navigate to movie details
            })
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun PopularMovies(
    navController: NavController,
    viewModel: HomeViewModel,
    movies: Flow<PagingData<Movie>>
) {
    val moviesList = movies.collectAsLazyPagingItems()

    SectionSeparator(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 12.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        sectionTitle = stringResource(id = R.string.popular_movies),
        onItemClick = {
            //ToDo: OnSectionedClicked-navigate to view all
        }
    )

    Spacer(modifier = Modifier.height(8.dp))

    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        items(items=moviesList) { item ->
            ItemPopularMovies(viewModel = viewModel, movie = item!!, onClickItem = {

            })

        }
    }

}

@Composable
fun UpcomingMovies(navController: NavController, movies: Flow<PagingData<Movie>>
) {
    val moviesList = movies.collectAsLazyPagingItems()

    Column(modifier = Modifier.padding(bottom = 90.dp)) {
        SectionSeparator(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 12.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            sectionTitle = stringResource(id = R.string.upcoming_movies),
            onItemClick = {
                //ToDo: OnSectionedClicked-navigate to view all
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(items = moviesList) { item ->
                ItemRecentlyPlayedAlbum(movie = item!!, onItemClick = {
                    //ToDo: OnItemClicked-Navigate to movie details
                })
            }
        }
    }
}

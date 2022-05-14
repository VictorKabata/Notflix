package com.vickikbt.notflix.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.placeholder
import com.vickikbt.notflix.R
import com.vickikbt.notflix.ui.components.ItemNowPlayingMovies
import com.vickikbt.notflix.ui.components.ItemPopularMovies
import com.vickikbt.notflix.ui.components.ItemTrendingMovies
import com.vickikbt.notflix.ui.components.SectionSeparator
import com.vickikbt.notflix.ui.theme.DarkPrimaryColor
import com.vickikbt.notflix.ui.theme.Gray
import com.vickikbt.shared.domain.models.Movie
import org.koin.androidx.compose.getViewModel

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

            NowPlayingMovies(
                navController = navController,
                viewModel = viewModel,
                movies = nowPlayingMovies ?: listOf()
            )

            trendingMovies?.let {
                TrendingMovies(
                    navController = navController,
                    movies = it
                )
            }

            popularMovies?.let {
                PopularMovies(
                    navController = navController,
                    viewModel = viewModel,
                    movies = it
                )
            }

            upcomingMovies?.let {
                UpcomingMovies(
                    navController = navController,
                    movies = it
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
                .height(360.dp)
                .placeholder(
                    visible = movies.isNullOrEmpty(),
                    color = Gray,
                    highlight = PlaceholderHighlight.fade(highlightColor = Color.Transparent)
                ),
            viewModel = viewModel,
            movie = movies[page]
        ) {
            val movie = movies[page]
            navController.navigate("details/${movie.id!!}/${movie.cacheId!!}")
        }
    }

    Spacer(modifier = Modifier.height(6.dp))

    HorizontalPagerIndicator(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .placeholder(
                visible = movies.isNullOrEmpty(),
                color = Gray,
                highlight = PlaceholderHighlight.fade(highlightColor = Color.Transparent)
            ),
        pagerState = pagerState,
        indicatorHeight = 6.dp,
        indicatorWidth = 6.dp,
        spacing = 6.dp,
        activeColor = DarkPrimaryColor,
        inactiveColor = Gray
    )
}

@Composable
fun TrendingMovies(navController: NavController, movies: List<Movie>) {

    SectionSeparator(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 12.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        sectionTitle = stringResource(id = R.string.trending_movies),
        onItemClick = {
            // ToDo: OnSectionedClicked-navigate to view all
        }
    )

    Spacer(modifier = Modifier.height(8.dp))

    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(items = movies) { item ->
            ItemTrendingMovies(
                modifier = Modifier.placeholder(
                    visible = movies.isNullOrEmpty(),
                    color = Gray,
                    shape = RoundedCornerShape(4.dp),
                    highlight = PlaceholderHighlight.fade(highlightColor = Color.Transparent)
                ),
                movie = item,
                onItemClick = { movie ->
                    navController.navigate("details/${movie.id!!}/${movie.cacheId}")
                }
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun PopularMovies(
    navController: NavController,
    viewModel: HomeViewModel,
    movies: List<Movie>
) {
    SectionSeparator(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 12.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        sectionTitle = stringResource(id = R.string.popular_movies),
        onItemClick = {
            // ToDo: OnSectionedClicked-navigate to view all
        }
    )

    Spacer(modifier = Modifier.height(8.dp))

    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        items(items = movies) { item ->
            ItemPopularMovies(
                modifier = Modifier.placeholder(
                    visible = movies.isNullOrEmpty(),
                    color = Gray,
                    shape = RoundedCornerShape(4.dp),
                    highlight = PlaceholderHighlight.fade(highlightColor = Color.Transparent)
                ),
                viewModel = viewModel,
                movie = item,
                onClickItem = { movie ->
                    navController.navigate("details/${movie.id!!}/${movie.cacheId}")
                }
            )
        }
    }
}

@Composable
fun UpcomingMovies(
    navController: NavController,
    movies: List<Movie>
) {
    Column(modifier = Modifier.padding(bottom = 90.dp)) {
        SectionSeparator(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 12.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            sectionTitle = stringResource(id = R.string.upcoming_movies),
            onItemClick = {
                // ToDo: OnSectionedClicked-navigate to view all
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(items = movies) { item ->
                ItemTrendingMovies(
                    modifier = Modifier.placeholder(
                        visible = movies.isNullOrEmpty(),
                        color = Gray,
                        shape = RoundedCornerShape(4.dp),
                        highlight = PlaceholderHighlight.fade(highlightColor = Color.Transparent)
                    ),
                    movie = item,
                    onItemClick = { movie ->
                        navController.navigate("details/${movie.id!!}/${movie.cacheId}")
                    }
                )
            }
        }
    }
}

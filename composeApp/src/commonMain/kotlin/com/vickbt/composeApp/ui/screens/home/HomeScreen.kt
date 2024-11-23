package com.vickbt.composeApp.ui.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import app.cash.paging.compose.collectAsLazyPagingItems
import com.kmpalette.loader.rememberNetworkLoader
import com.vickbt.composeApp.domain.models.Movie
import com.vickbt.composeApp.domain.models.TvShow
import com.vickbt.composeApp.domain.utils.Enums
import com.vickbt.composeApp.ui.components.FilterHome
import com.vickbt.composeApp.ui.components.MovieCardLandscape
import com.vickbt.composeApp.ui.components.MovieCardPager
import com.vickbt.composeApp.ui.components.MovieCardPagerIndicator
import com.vickbt.composeApp.ui.components.MovieCardPortraitCompact
import com.vickbt.composeApp.ui.components.SectionSeparator
import com.vickbt.composeApp.ui.components.appbars.AppBar
import com.vickbt.composeApp.ui.navigation.NavigationItem
import com.vickbt.composeApp.utils.WindowSize
import com.vickbt.shared.resources.Res
import com.vickbt.shared.resources.logo_n
import com.vickbt.shared.resources.popular_movies
import com.vickbt.shared.resources.top_rated_tv_shows
import com.vickbt.shared.resources.trending_movies
import com.vickbt.shared.resources.trending_tv_shows
import com.vickbt.shared.resources.upcoming_movies
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import kotlin.random.Random

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navigator: NavHostController,
    windowSize: WindowSize = WindowSize.COMPACT,
    viewModel: HomeViewModel = koinViewModel<HomeViewModel>(),
    mainPaddingValues: PaddingValues
) {
    val scrollState = rememberScrollState()

    val homeUiState = viewModel.homeUiState.collectAsState().value

    val networkLoader = rememberNetworkLoader(httpClient = koinInject())

    var mediaTypeSelected by remember { mutableStateOf<Enums.MediaType?>(null) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(mainPaddingValues),
        topBar = {
            AppBar(
                headerIcon = Res.drawable.logo_n,
                onActionClicked = { navigator.navigate(NavigationItem.Search.route) }
            )
        }
    ) { paddingValues ->

        // region Home section
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (homeUiState.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else if (!homeUiState.error.isNullOrEmpty()) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "Error:\n${homeUiState.error}",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineMedium
                )
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    //region Home Filters
                    FilterHome(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        onFilterClicked = { mediaTypeSelected = it },
                        onFilterClosed = { mediaTypeSelected = null }
                    )
                    //endregion

                    //region Now Playing Movies or Airing Today TV Shows
                    val headerDisplay =
                        if (Random.nextBoolean() && mediaTypeSelected == Enums.MediaType.MOVIE) homeUiState.nowPlayingMovies else homeUiState.airingTodayTvShows

                    headerDisplay?.let {
                        if (it == homeUiState.airingTodayTvShows) {
                            val pagerState =
                                rememberPagerState(pageCount = { it.size })

                            HorizontalPager(
                                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                                state = pagerState,
                                contentPadding = PaddingValues(horizontal = 16.dp),
                                pageSpacing = 8.dp
                            ) { currentPage ->
                                val tvShow = it[currentPage] as TvShow
                                MovieCardPager(
                                    modifier = Modifier.fillMaxWidth().height(420.dp),
                                    networkLoader = networkLoader,
                                    movieId = tvShow.id,
                                    backdropPath = tvShow.backdropPath,
                                    title = tvShow.name,
                                    voteAverage = tvShow.voteAverage
                                ) { id ->
                                    navigator.navigate("/details/$id")
                                }
                            }

                            MovieCardPagerIndicator(
                                modifier = Modifier.padding(vertical = 6.dp),
                                pagerState = pagerState,
                                indicatorSize = 6.dp,
                                inactiveColor = Color.Gray,
                                activeColor = MaterialTheme.colorScheme.primary
                            )
                        } else {
                            val pagerState =
                                rememberPagerState(pageCount = { it.size })

                            HorizontalPager(
                                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                                state = pagerState,
                                contentPadding = PaddingValues(horizontal = 16.dp),
                                pageSpacing = 8.dp
                            ) { currentPage ->
                                val movie = it[currentPage] as Movie
                                MovieCardPager(
                                    modifier = Modifier.fillMaxWidth().height(420.dp),
                                    networkLoader = networkLoader,
                                    movieId = movie.id,
                                    backdropPath = movie.backdropPath,
                                    title = movie.title,
                                    voteAverage = movie.voteAverage
                                ) { id ->
                                    navigator.navigate("/details/$id")
                                }
                            }

                            MovieCardPagerIndicator(
                                modifier = Modifier.padding(vertical = 6.dp),
                                pagerState = pagerState,
                                indicatorSize = 6.dp,
                                inactiveColor = Color.Gray,
                                activeColor = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                    //endregion

                    //region Trending Movies
                    AnimatedVisibility(visible = mediaTypeSelected == null || mediaTypeSelected == Enums.MediaType.MOVIE) {
                        homeUiState.trendingMovies?.let { movies ->
                            val trendingMovies = movies.collectAsLazyPagingItems()

                            Column {
                                SectionSeparator(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight(),
                                    sectionTitle = stringResource(Res.string.trending_movies)
                                )

                                LazyRow(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight(),
                                    contentPadding = PaddingValues(horizontal = 16.dp),
                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    items(trendingMovies.itemCount) { index ->
                                        trendingMovies[index]?.let {
                                            MovieCardPortraitCompact(
                                                movieId = it.id,
                                                posterPath = it.posterPath,
                                                title = it.title,
                                                onItemClick = { id ->
                                                    navigator.navigate("/details/$id")
                                                }
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                    //endregion

                    //region Top Rated Tv Shows
                    AnimatedVisibility(visible = mediaTypeSelected == null || mediaTypeSelected == Enums.MediaType.TV_SHOW) {
                        homeUiState.topRatedTvShows?.let { movies ->
                            val topRatedTvShows = movies.collectAsLazyPagingItems()

                            Column {
                                SectionSeparator(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight(),
                                    sectionTitle = stringResource(Res.string.top_rated_tv_shows)
                                )

                                LazyRow(
                                    contentPadding = PaddingValues(horizontal = 16.dp),
                                    horizontalArrangement = Arrangement.spacedBy(14.dp),
                                    modifier = Modifier.wrapContentHeight()
                                ) {
                                    items(topRatedTvShows.itemCount) { index ->
                                        topRatedTvShows[index]?.let {
                                            MovieCardLandscape(
                                                modifier = Modifier
                                                    .width(300.dp)
                                                    .height(245.dp),
                                                movieId = it.id,
                                                backdropPath = it.backdropPath,
                                                title = it.name,
                                                voteAverage = it.voteAverage,
                                                releaseDate = it.firstAirDate,
                                                networkLoader = networkLoader,
                                                onClickItem = { id ->
                                                    navigator.navigate("/details/$id")
                                                }
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                    //endregion

                    //region Upcoming Movies
                    AnimatedVisibility(visible = mediaTypeSelected == null || mediaTypeSelected == Enums.MediaType.MOVIE) {
                        homeUiState.upcomingMovies?.let { movies ->
                            val upcomingMovies = movies.collectAsLazyPagingItems()

                            Column(modifier = Modifier) {
                                SectionSeparator(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight(),
                                    sectionTitle = stringResource(Res.string.upcoming_movies)
                                )

                                LazyRow(
                                    modifier = Modifier.wrapContentHeight(),
                                    contentPadding = PaddingValues(horizontal = 16.dp),
                                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                                ) {
                                    items(upcomingMovies.itemCount) { index ->
                                        upcomingMovies[index]?.let {
                                            MovieCardPortraitCompact(
                                                movieId = it.id,
                                                posterPath = it.posterPath,
                                                title = it.title,
                                                onItemClick = { id ->
                                                    navigator.navigate("/details/$id")
                                                }
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                    //endregion

                    //region Trending Tv Shows
                    AnimatedVisibility(visible = mediaTypeSelected == null || mediaTypeSelected == Enums.MediaType.TV_SHOW) {
                        homeUiState.trendingTvShows?.let { tvShows ->
                            val trendingTvShows = tvShows.collectAsLazyPagingItems()

                            Column {
                                SectionSeparator(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight(),
                                    sectionTitle = stringResource(Res.string.trending_tv_shows)
                                )

                                LazyRow(
                                    contentPadding = PaddingValues(horizontal = 16.dp),
                                    horizontalArrangement = Arrangement.spacedBy(14.dp),
                                    modifier = Modifier.wrapContentHeight()
                                ) {
                                    items(trendingTvShows.itemCount) { index ->
                                        trendingTvShows[index]?.let {
                                            MovieCardLandscape(
                                                modifier = Modifier
                                                    .width(300.dp)
                                                    .height(245.dp),
                                                movieId = it.id,
                                                backdropPath = it.backdropPath,
                                                title = it.name,
                                                voteAverage = it.voteAverage,
                                                releaseDate = it.firstAirDate,
                                                networkLoader = networkLoader,
                                                onClickItem = { id ->
                                                    navigator.navigate("/details/$id")
                                                }
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                    //endregion

                    //region Popular Movies
                    AnimatedVisibility(visible = mediaTypeSelected == null || mediaTypeSelected == Enums.MediaType.MOVIE) {
                        homeUiState.popularMovies?.let { movies ->
                            val popularMovies = movies.collectAsLazyPagingItems()

                            Column {
                                SectionSeparator(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight(),
                                    sectionTitle = stringResource(Res.string.popular_movies)
                                )

                                LazyRow(
                                    contentPadding = PaddingValues(horizontal = 16.dp),
                                    horizontalArrangement = Arrangement.spacedBy(14.dp),
                                    modifier = Modifier.wrapContentHeight()
                                ) {
                                    items(popularMovies.itemCount) { index ->
                                        popularMovies[index]?.let {
                                            MovieCardLandscape(
                                                modifier = Modifier
                                                    .width(300.dp)
                                                    .height(245.dp),
                                                movieId = it.id,
                                                backdropPath = it.backdropPath,
                                                title = it.title,
                                                voteAverage = it.voteAverage,
                                                releaseDate = it.releaseDate,
                                                networkLoader = networkLoader,
                                                onClickItem = { id ->
                                                    navigator.navigate("/details/$id")
                                                }
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                    //endregion

                    //region Popular Tv Shows
                    AnimatedVisibility(visible = mediaTypeSelected == null || mediaTypeSelected == Enums.MediaType.TV_SHOW) {
                        homeUiState.popularTvShows?.let { tvShows ->
                            val popularTvShows = tvShows.collectAsLazyPagingItems()

                            Column {
                                SectionSeparator(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight(),
                                    sectionTitle = stringResource(Res.string.trending_tv_shows)
                                )

                                LazyRow(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight(),
                                    contentPadding = PaddingValues(horizontal = 16.dp),
                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    items(popularTvShows.itemCount) { index ->
                                        popularTvShows[index]?.let {
                                            MovieCardPortraitCompact(
                                                movieId = it.id,
                                                posterPath = it.posterPath,
                                                title = it.name,
                                                onItemClick = { id ->
                                                    navigator.navigate("/details/$id")
                                                }
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                    //endregion
                }
            }
            // endregion
        }
    }
}

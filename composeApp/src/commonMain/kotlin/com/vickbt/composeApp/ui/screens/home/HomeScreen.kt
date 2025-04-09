package com.vickbt.composeApp.ui.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import app.cash.paging.compose.collectAsLazyPagingItems
import com.kmpalette.loader.rememberNetworkLoader
import com.vickbt.composeApp.domain.utils.Enums
import com.vickbt.composeApp.ui.components.FilterHome
import com.vickbt.composeApp.ui.components.MovieCardLandscape
import com.vickbt.composeApp.ui.components.MovieCardPager
import com.vickbt.composeApp.ui.components.MovieCardPagerIndicator
import com.vickbt.composeApp.ui.components.MovieCardPortraitCompact
import com.vickbt.composeApp.ui.components.SectionSeparator
import com.vickbt.composeApp.ui.components.appbars.AppBar
import com.vickbt.composeApp.ui.navigation.NavigationItem
import com.vickbt.composeApp.ui.theme.DarkPrimaryColor
import com.vickbt.shared.resources.Res
import com.vickbt.shared.resources.logo_n
import com.vickbt.shared.resources.popular_movies
import com.vickbt.shared.resources.top_rated_tv_shows
import com.vickbt.shared.resources.trending_movies
import com.vickbt.shared.resources.trending_tv_shows
import com.vickbt.shared.resources.upcoming_movies
import network.chaintech.sdpcomposemultiplatform.sdp
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel<HomeViewModel>(),
    onNavigate: (String) -> Unit,
) {
    val scrollState = rememberScrollState()

    val homeUiState = viewModel.homeUiState.collectAsState().value

    val networkLoader = rememberNetworkLoader(httpClient = koinInject())

    var mediaTypeSelected by remember { mutableStateOf<Enums.MediaType?>(null) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppBar(
                headerIcon = Res.drawable.logo_n,
                onActionClicked = { onNavigate(NavigationItem.Search.route) }
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
                    style = MaterialTheme.typography.titleMedium
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
                        modifier = Modifier.padding(horizontal = 16.sdp, vertical = 8.sdp),
                        onFilterClicked = { mediaTypeSelected = it },
                        onFilterClosed = { mediaTypeSelected = null }
                    )
                    //endregion

                    //region Now Playing Movies
                    homeUiState.nowPlayingMovies?.let { nowPlayingMovies ->
                        val pagerState =
                            rememberPagerState(pageCount = { nowPlayingMovies.size })

                        HorizontalPager(
                            modifier = Modifier.fillMaxWidth().padding(vertical = 12.sdp),
                            state = pagerState,
                            contentPadding = PaddingValues(horizontal = 16.sdp),
                            pageSpacing = 8.sdp
                        ) { currentPage ->
                            MovieCardPager(
                                modifier = Modifier.fillMaxWidth().height(336.sdp),
                                networkLoader = networkLoader,
                                movie = nowPlayingMovies[currentPage]
                            ) { movie ->
                                onNavigate("/details/${movie.id}")
                            }
                        }

                        MovieCardPagerIndicator(
                            modifier = Modifier.padding(vertical = 6.sdp),
                            pagerState = pagerState,
                            indicatorSize = 6.sdp,
                            inactiveColor = Color.Gray,
                            activeColor = DarkPrimaryColor
                        )
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
                                    contentPadding = PaddingValues(horizontal = 16.sdp),
                                    horizontalArrangement = Arrangement.spacedBy(4.sdp)
                                ) {
                                    items(trendingMovies.itemCount) { index ->
                                        trendingMovies[index]?.let {
                                            MovieCardPortraitCompact(
                                                movieId = it.id,
                                                posterPath = it.posterPath,
                                                title = it.title,
                                                onItemClick = { id ->
                                                    onNavigate("/details/$id")
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
                                    contentPadding = PaddingValues(horizontal = 16.sdp),
                                    horizontalArrangement = Arrangement.spacedBy(4.sdp),
                                    modifier = Modifier.wrapContentHeight()
                                ) {
                                    items(topRatedTvShows.itemCount) { index ->
                                        topRatedTvShows[index]?.let {
                                            MovieCardLandscape(
                                                modifier = Modifier,
                                                movieId = it.id,
                                                backdropPath = it.backdropPath,
                                                title = it.name,
                                                voteAverage = it.voteAverage,
                                                releaseDate = it.firstAirDate,
                                                networkLoader = networkLoader,
                                                onClickItem = { id ->
                                                    onNavigate("/details/$id")
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
                                    contentPadding = PaddingValues(horizontal = 16.sdp),
                                    horizontalArrangement = Arrangement.spacedBy(4.sdp),
                                ) {
                                    items(upcomingMovies.itemCount) { index ->
                                        upcomingMovies[index]?.let {
                                            MovieCardPortraitCompact(
                                                movieId = it.id,
                                                posterPath = it.posterPath,
                                                title = it.title,
                                                onItemClick = { id ->
                                                    onNavigate("/details/$id")
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
                                    contentPadding = PaddingValues(horizontal = 16.sdp),
                                    horizontalArrangement = Arrangement.spacedBy(4.sdp),
                                    modifier = Modifier.wrapContentHeight()
                                ) {
                                    items(trendingTvShows.itemCount) { index ->
                                        trendingTvShows[index]?.let {
                                            MovieCardLandscape(
                                                modifier = Modifier,
                                                movieId = it.id,
                                                backdropPath = it.backdropPath,
                                                title = it.name,
                                                voteAverage = it.voteAverage,
                                                releaseDate = it.firstAirDate,
                                                networkLoader = networkLoader,
                                                onClickItem = { id ->
                                                    onNavigate("/details/$id")
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
                                    contentPadding = PaddingValues(horizontal = 16.sdp),
                                    horizontalArrangement = Arrangement.spacedBy(4.sdp),
                                    modifier = Modifier.wrapContentHeight()
                                ) {
                                    items(popularMovies.itemCount) { index ->
                                        popularMovies[index]?.let {
                                            MovieCardLandscape(
                                                modifier = Modifier,
                                                movieId = it.id,
                                                backdropPath = it.backdropPath,
                                                title = it.title,
                                                voteAverage = it.voteAverage,
                                                releaseDate = it.releaseDate,
                                                networkLoader = networkLoader,
                                                onClickItem = { id ->
                                                    onNavigate("/details/$id")
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
                                    contentPadding = PaddingValues(horizontal = 16.sdp),
                                    horizontalArrangement = Arrangement.spacedBy(4.sdp)
                                ) {
                                    items(popularTvShows.itemCount) { index ->
                                        popularTvShows[index]?.let {
                                            MovieCardPortraitCompact(
                                                movieId = it.id,
                                                posterPath = it.posterPath,
                                                title = it.name,
                                                onItemClick = { id ->
                                                    onNavigate("/details/$id")
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

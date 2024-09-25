package com.vickbt.composeApp.ui.screens.home

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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import app.cash.paging.compose.collectAsLazyPagingItems
import com.vickbt.composeApp.ui.components.MovieCardLandscape
import com.vickbt.composeApp.ui.components.MovieCardPager
import com.vickbt.composeApp.ui.components.MovieCardPagerIndicator
import com.vickbt.composeApp.ui.components.MovieCardPortraitCompact
import com.vickbt.composeApp.ui.components.SectionSeparator
import com.vickbt.composeApp.ui.components.appbars.AppBar
import com.vickbt.composeApp.ui.theme.DarkPrimaryColor
import com.vickbt.composeApp.utils.WindowSize
import com.vickbt.shared.resources.Res
import com.vickbt.shared.resources.popular_movies
import com.vickbt.shared.resources.trending_movies
import com.vickbt.shared.resources.upcoming_movies
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

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

    val movies = homeUiState.trendingMovies?.collectAsLazyPagingItems()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(mainPaddingValues),
        topBar = { AppBar("Home") }
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
                    textAlign = TextAlign.Center
                )
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    //region Now Playing Movies
                    homeUiState.nowPlayingMovies?.let { nowPlayingMovies ->
                        val pagerState =
                            rememberPagerState(pageCount = { nowPlayingMovies.size })

                        HorizontalPager(
                            modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                            state = pagerState,
                            contentPadding = PaddingValues(horizontal = 16.dp),
                            pageSpacing = 8.dp
                        ) { currentPage ->
                            MovieCardPager(
                                modifier = Modifier.fillMaxWidth().height(280.dp),
                                movie = nowPlayingMovies[currentPage]
                            ) { movie ->
                                navigator.navigate("/details/${movie.id}")
                            }
                        }

                        MovieCardPagerIndicator(
                            modifier = Modifier.padding(vertical = 6.dp),
                            pagerState = pagerState,
                            indicatorSize = 6.dp,
                            inactiveColor = Color.Gray,
                            activeColor = DarkPrimaryColor
                        )
                    }
                    //endregion

                    //region Trending Movies
                    homeUiState.trendingMovies?.let { movies ->
                        val trendingMovies = movies.collectAsLazyPagingItems()

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
                                        movie = it,
                                        onItemClick = { movie ->
                                            navigator.navigate("/details/${movie.id}")
                                        }
                                    )
                                }
                            }
                        }
                    }
                    //endregion

                    //region Upcoming Movies
                    homeUiState.upcomingMovies?.let { movies ->
                        val upcomingMovies = movies.collectAsLazyPagingItems()

                        SectionSeparator(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            sectionTitle = stringResource(Res.string.upcoming_movies)
                        )

                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(14.dp),
                            modifier = Modifier.wrapContentHeight()
                        ) {
                            items(upcomingMovies.itemCount) { index ->
                                upcomingMovies[index]?.let {
                                    MovieCardLandscape(
                                        modifier = Modifier
                                            .width(300.dp)
                                            .height(245.dp),
                                        movie = it,
                                        onClickItem = { movie ->
                                            navigator.navigate("/details/${movie.id}")
                                        }
                                    )
                                }
                            }
                        }
                    }
                    //endregion

                    //region Popular Movies
                    homeUiState.popularMovies?.let { movies ->
                        val popularMovies = movies.collectAsLazyPagingItems()

                        Column(modifier = Modifier.padding(bottom = 90.dp)) {
                            SectionSeparator(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight(),
                                sectionTitle = stringResource(Res.string.popular_movies)
                            )

                            LazyRow(
                                modifier = Modifier.wrapContentHeight(),
                                contentPadding = PaddingValues(horizontal = 16.dp),
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                            ) {
                                items(popularMovies.itemCount) { index ->
                                    popularMovies[index]?.let {
                                        MovieCardPortraitCompact(
                                            movie = it,
                                            onItemClick = { movie ->
                                                navigator.navigate("/details/${movie.id}")
                                            }
                                        )
                                    }
                                }
                            }
                        }
                        //endregion
                    }
                }
            }
            // endregion
        }
    }
}

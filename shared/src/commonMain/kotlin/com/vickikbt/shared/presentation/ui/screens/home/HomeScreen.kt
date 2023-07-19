package com.vickikbt.shared.presentation.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vickikbt.shared.presentation.ui.components.ItemNowPlayingMovies
import com.vickikbt.shared.presentation.ui.components.ItemPopularMovies
import com.vickikbt.shared.presentation.ui.components.ItemTrendingMovies
import com.vickikbt.shared.presentation.ui.components.SectionSeparator
import org.koin.compose.koinInject

@ExperimentalMaterialApi
@Composable
fun HomeScreen(viewModel: HomeViewModel = koinInject()) {
    val scrollState = rememberScrollState()
    // val pagerState = rememberPagerState()

    LaunchedEffect(key1 = viewModel) {
        viewModel.fetchNowPlayingMovies()
        viewModel.fetchTrendingMovies()
        viewModel.fetchPopularMovies()
        viewModel.fetchUpcomingMovies()
    }

    val homeUiState = viewModel.homeUiState.collectAsState().value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.surface)
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
                homeUiState.nowPlayingMovies?.let {
                    ItemNowPlayingMovies(
                        modifier = Modifier.fillMaxSize(),
                        movie = it[0]
                    ) {
                        // ToDo: Navigate to details
                    }
                }
                //endregion

                //region Trending Movies
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

                Spacer(modifier = Modifier.height(8.dp))

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(items = homeUiState.trendingMovies ?: emptyList()) { item ->
                        ItemTrendingMovies(
                            movie = item,
                            onItemClick = { movie ->
                                // ToDo: Navigate to details
                            }
                        )
                    }
                }
                //endregion

                //region Upcoming Movies
                homeUiState.upcomingMovies?.let {
                    SectionSeparator(
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, top = 12.dp)
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        sectionTitle = "Upcoming Movies",
                        onItemClick = {
                            // ToDo: OnSectionedClicked-navigate to view all
                        }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(14.dp),
                        modifier = Modifier.wrapContentHeight()
                    ) {
                        items(items = it) { item ->
                            ItemPopularMovies(
                                modifier = Modifier
                                    .width(300.dp)
                                    .height(245.dp),
                                movie = item,
                                onClickItem = { movie ->
                                    // ToDo: Navigate to details
                                }
                            )
                        }
                    }
                }
                //endregion

                //region Popular Movies
                homeUiState.popularMovies?.let {
                    Column(modifier = Modifier.padding(bottom = 90.dp)) {
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

                        Spacer(modifier = Modifier.height(8.dp))

                        LazyRow(
                            modifier = Modifier.wrapContentHeight(),
                            contentPadding = PaddingValues(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                        ) {
                            items(items = it) { item ->
                                ItemTrendingMovies(
                                    movie = item,
                                    onItemClick = { movie ->
                                        // ToDo: Navigate to details
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
}

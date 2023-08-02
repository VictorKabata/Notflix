package com.vickikbt.shared.ui.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.vickikbt.shared.presentation.ui.screens.details.DetailsViewModel
import com.vickikbt.shared.ui.components.ItemMovieCast
import com.vickikbt.shared.ui.components.MovieCardPortrait
import com.vickikbt.shared.ui.components.MovieRatingSection
import com.vickikbt.shared.ui.components.appbars.COLLAPSED_TOP_BAR_HEIGHT
import com.vickikbt.shared.ui.components.appbars.CollapsedTopBar
import com.vickikbt.shared.ui.components.appbars.EXPANDED_TOP_BAR_HEIGHT
import com.vickikbt.shared.ui.components.appbars.ExpandedTopBar
import com.vickikbt.shared.utils.getPopularity
import com.vickikbt.shared.utils.getRating
import moe.tlaster.precompose.navigation.Navigator
import org.koin.compose.koinInject

@Composable
fun DetailsScreen(
    navigator: Navigator,
    viewModel: DetailsViewModel = koinInject(),
    movieId: Int
) {
    LaunchedEffect(key1 = viewModel) {
        viewModel.getMovieDetails(movieId)
        viewModel.fetchSimilarMovies(movieId)
        viewModel.getMovieCast(movieId)
    }

    val movieDetailsState = viewModel.movieDetailsState.collectAsState().value

    val lazyListState = rememberLazyListState()

    val overlapHeightPx = with(LocalDensity.current) {
        EXPANDED_TOP_BAR_HEIGHT.toPx() - COLLAPSED_TOP_BAR_HEIGHT.toPx()
    }
    val isCollapsed: Boolean by remember {
        derivedStateOf {
            val isFirstItemHidden =
                lazyListState.firstVisibleItemScrollOffset > overlapHeightPx
            isFirstItemHidden || lazyListState.firstVisibleItemIndex > 0
        }
    }

    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.surface)) {
        if (movieDetailsState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else if (!movieDetailsState.error.isNullOrEmpty()) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Error:\n${movieDetailsState.error}",
                textAlign = TextAlign.Center
            )
        } else {
            CollapsedTopBar(
                modifier = Modifier.zIndex(2f),
                isCollapsed = isCollapsed,
                movieDetails = movieDetailsState.movieDetails
            )

            LazyColumn(
                modifier = Modifier.padding(bottom = 20.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp),
                state = lazyListState
            ) {
                item {
                    ExpandedTopBar(movieDetails = movieDetailsState.movieDetails)
                }

                //region Movie Ratings
                movieDetailsState.movieDetails?.voteAverage?.let { voteAverage ->
                    item {
                        MovieRatingSection(
                            popularity = voteAverage.getPopularity(),
                            voteAverage = voteAverage.getRating()
                        )
                    }
                }
                //endregion

                //region Movie Overview
                movieDetailsState.movieDetails?.overview?.let {
                    item {
                        Text(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            text = "Overview",
                            style = MaterialTheme.typography.h6,
                            fontSize = 20.sp,
                            color = MaterialTheme.colors.onSurface,
                        )

                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            text = it,
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.onSurface,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Start,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }
                //endregion

                //region Movie Cast
                movieDetailsState.movieCast?.let {
                    item {
                        Text(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            text = "Cast",
                            style = MaterialTheme.typography.h6,
                            fontSize = 20.sp
                        )

                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            items(items = it) { item ->
                                ItemMovieCast(modifier = Modifier, actor = item)
                            }
                        }
                    }
                }
                //endregion

                //region Similar Movies
                movieDetailsState.similarMovies?.let {
                    item {
                        Text(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            text = "Similar Movies",
                            style = MaterialTheme.typography.h6,
                            fontSize = 20.sp,
                            color = MaterialTheme.colors.onSurface
                        )

                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            items(items = it) { movie ->
                                MovieCardPortrait(movie = movie, onItemClick = {})
                            }
                        }
                    }
                }
                //endregion
            }
        }
    }
}

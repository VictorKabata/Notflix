@file:OptIn(KoinExperimentalAPI::class)

package com.vickbt.composeApp.ui.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import app.cash.paging.compose.collectAsLazyPagingItems
import com.kmpalette.loader.rememberNetworkLoader
import com.vickbt.composeApp.ui.components.ItemMovieCast
import com.vickbt.composeApp.ui.components.MovieCardPortrait
import com.vickbt.composeApp.ui.components.MovieRatingSection
import com.vickbt.composeApp.ui.components.appbars.DetailsAppBar
import com.vickbt.composeApp.ui.components.collapsingToolbar.CollapsingToolbarScaffold
import com.vickbt.composeApp.ui.components.collapsingToolbar.ScrollStrategy
import com.vickbt.composeApp.ui.components.collapsingToolbar.rememberCollapsingToolbarScaffoldState
import com.vickbt.composeApp.utils.WindowSize
import com.vickbt.composeApp.utils.getPopularity
import com.vickbt.composeApp.utils.getRating
import com.vickbt.shared.resources.Res
import com.vickbt.shared.resources.cast
import com.vickbt.shared.resources.overview
import com.vickbt.shared.resources.similar_movies
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun DetailsScreen(
    navigator: NavHostController,
    windowSize: WindowSize = WindowSize.COMPACT,
    viewModel: DetailsViewModel = koinViewModel<DetailsViewModel>(),
    movieId: Int
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.getMovieDetails(movieId = movieId)
        viewModel.fetchSimilarMovies(movieId = movieId)
        viewModel.getMovieCast(movieId = movieId)
        viewModel.isMovieFavorite(movieId = movieId)
    }

    val networkLoader = rememberNetworkLoader(httpClient = koinInject())

    val movieDetailsState = viewModel.movieDetailsState.collectAsState().value

    val scrollState = rememberScrollState()
    val collapsingScrollState = rememberCollapsingToolbarScaffoldState()

    Box(modifier = Modifier.fillMaxSize()) {
        if (movieDetailsState.isLoading && movieDetailsState.movieDetails == null) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else if (!movieDetailsState.error.isNullOrEmpty()) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Error:\n${movieDetailsState.error}",
                textAlign = TextAlign.Center
            )
        } else {
            CollapsingToolbarScaffold(
                modifier = Modifier.fillMaxSize(),
                state = collapsingScrollState,
                scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
                toolbar = {
                    DetailsAppBar(
                        modifier = Modifier.fillMaxWidth(),
                        collapsingScrollState = collapsingScrollState,
                        movieDetailsState = movieDetailsState,
                        networkLoader = networkLoader,
                        onNavigationIconClick = { navigator.navigateUp() },
                        onShareIconClick = {},
                        onFavoriteIconClick = { movieDetails, isFavorite ->
                            if (isFavorite == true) {
                                viewModel.saveFavoriteMovie(movieDetails = movieDetails)
                            } else {
                                viewModel.deleteFavoriteMovie(movieId = movieDetails.id)
                            }
                        }
                    )
                }
            ) {
                Column(
                    modifier = Modifier.padding(bottom = 20.dp).verticalScroll(state = scrollState),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    //region Movie Ratings
                    if (movieDetailsState.movieDetails?.voteAverage != null) {
                        MovieRatingSection(
                            popularity = movieDetailsState.movieDetails.voteAverage.getPopularity(),
                            voteAverage = movieDetailsState.movieDetails.voteAverage.getRating()
                        )
                    }
                    //endregion

                    //region Movie Overview
                    if (!movieDetailsState.movieDetails?.overview.isNullOrEmpty()) {
                        Text(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            text = stringResource(Res.string.overview),
                            style = MaterialTheme.typography.titleMedium,
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.onSurface,
                        )

                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            text = movieDetailsState.movieDetails?.overview ?: "",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Start,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                    //endregion

                    //region Movie Cast
                    if (!movieDetailsState.movieCast.isNullOrEmpty()) {
                        Text(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            text = stringResource(Res.string.cast),
                            style = MaterialTheme.typography.titleMedium,
                            fontSize = 20.sp
                        )

                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            items(items = movieDetailsState.movieCast) { item ->
                                ItemMovieCast(modifier = Modifier, actor = item)
                            }
                        }
                    }
                    //endregion

                    //region Similar Movies
                    movieDetailsState.similarMovies?.let {
                        val similarMovies = it.collectAsLazyPagingItems()
                        Text(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            text = stringResource(Res.string.similar_movies),
                            style = MaterialTheme.typography.titleMedium,
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            items(similarMovies.itemCount) { index ->
                                similarMovies[index]?.let { movie ->
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
}

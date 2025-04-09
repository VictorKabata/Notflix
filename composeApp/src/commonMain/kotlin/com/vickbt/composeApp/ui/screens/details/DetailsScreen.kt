@file:OptIn(KoinExperimentalAPI::class)

package com.vickbt.composeApp.ui.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import app.cash.paging.compose.collectAsLazyPagingItems
import com.kmpalette.loader.rememberNetworkLoader
import com.vickbt.composeApp.ui.components.ItemMovieCast
import com.vickbt.composeApp.ui.components.MovieCardPortrait
import com.vickbt.composeApp.ui.components.MovieRatingSection
import com.vickbt.composeApp.ui.components.SectionSeparator
import com.vickbt.composeApp.ui.components.appbars.DetailsAppBar
import com.vickbt.composeApp.ui.components.collapsingToolbar.CollapsingToolbarScaffold
import com.vickbt.composeApp.ui.components.collapsingToolbar.ScrollStrategy
import com.vickbt.composeApp.ui.components.collapsingToolbar.rememberCollapsingToolbarScaffoldState
import com.vickbt.composeApp.utils.getPopularity
import com.vickbt.composeApp.utils.getRating
import com.vickbt.shared.resources.Res
import com.vickbt.shared.resources.cast
import com.vickbt.shared.resources.overview
import com.vickbt.shared.resources.similar_movies
import network.chaintech.sdpcomposemultiplatform.sdp
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel = koinViewModel<DetailsViewModel>(),
    movieId: Int,
    onNavigate: (String?) -> Unit,
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.getMovieDetails(movieId = movieId)
        viewModel.fetchSimilarMovies(movieId = movieId)
        viewModel.getMovieCast(movieId = movieId)
        viewModel.isMovieFavorite(movieId = movieId)
    }

    val networkLoader = rememberNetworkLoader(httpClient = koinInject())

    val movieDetailsUiState by viewModel.movieDetailsState.collectAsState()

    val scrollState = rememberScrollState()
    val collapsingScrollState = rememberCollapsingToolbarScaffoldState()

    Box(modifier = Modifier.fillMaxSize()) {
        if (movieDetailsUiState.isLoading && movieDetailsUiState.movieDetails == null) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else if (!movieDetailsUiState.error.isNullOrEmpty()) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Error:\n${movieDetailsUiState.error}",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium
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
                        movieDetailsState = movieDetailsUiState,
                        networkLoader = networkLoader,
                        onNavigationIconClick = { onNavigate(null) },
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
                    modifier = Modifier.padding(vertical = 16.sdp)
                        .verticalScroll(state = scrollState)
                ) {
                    //region Movie Ratings
                    if (movieDetailsUiState.movieDetails?.voteAverage != null) {
                        MovieRatingSection(
                            popularity = movieDetailsUiState.movieDetails?.voteAverage?.getPopularity(),
                            voteAverage = movieDetailsUiState.movieDetails?.voteAverage?.getRating()
                        )
                    }
                    //endregion

                    //region Movie Overview
                    if (!movieDetailsUiState.movieDetails?.overview.isNullOrEmpty()) {
                        SectionSeparator(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            sectionTitle = stringResource(Res.string.overview)
                        )

                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.sdp),
                            text = movieDetailsUiState.movieDetails?.overview ?: "",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onBackground,
                            textAlign = TextAlign.Start,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                    //endregion

                    //region Movie Cast
                    movieDetailsUiState.movieCast?.let { cast ->
                        SectionSeparator(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            sectionTitle = stringResource(Res.string.cast)
                        )

                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 16.sdp),
                            horizontalArrangement = Arrangement.spacedBy(8.sdp)
                        ) {
                            items(items = cast) { item ->
                                ItemMovieCast(modifier = Modifier, actor = item)
                            }
                        }
                    }

                    //endregion

                    //region Similar Movies
                    movieDetailsUiState.similarMovies?.let {
                        val similarMovies = it.collectAsLazyPagingItems()
                        SectionSeparator(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            sectionTitle = stringResource(Res.string.similar_movies)
                        )

                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 16.sdp),
                            horizontalArrangement = Arrangement.spacedBy(4.sdp)
                        ) {
                            items(similarMovies.itemCount) { index ->
                                similarMovies[index]?.let { movie ->
                                    MovieCardPortrait(
                                        movie = movie,
                                        onItemClick = { onNavigate("/details/${movie.id}") }
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
}

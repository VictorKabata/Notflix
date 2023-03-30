package com.vickikbt.notflix.ui.screens.details

import android.content.Context
import android.content.Intent
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
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.vickikbt.notflix.R
import com.vickikbt.notflix.ui.components.ItemMovieCast
import com.vickikbt.notflix.ui.components.ItemSimilarMovies
import com.vickikbt.notflix.ui.components.MovieRatingSection
import com.vickikbt.notflix.ui.components.appbars.DetailsAppBar
import com.vickikbt.shared.utils.getPopularity
import com.vickikbt.shared.utils.getRating
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState
import org.koin.androidx.compose.get

@ExperimentalCoilApi
@Composable
fun DetailsScreen(
    navController: NavController,
    detailsViewModel: DetailsViewModel = get(),
    movieId: Int
) {
    LaunchedEffect(key1 = detailsViewModel) {
        detailsViewModel.getMovieDetails(movieId)
        detailsViewModel.fetchSimilarMovies(movieId)
        detailsViewModel.getMovieCast(movieId)
    }

    val movieDetailsState = detailsViewModel.movieDetailsState.collectAsState().value

    val context = LocalContext.current

    val scrollState = rememberScrollState()
    val collapsingScrollState = rememberCollapsingToolbarScaffoldState()

    Box(modifier = Modifier.fillMaxSize()) {
        if (movieDetailsState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else if (!movieDetailsState.error.isNullOrEmpty()) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Error:\n${movieDetailsState.error}",
                textAlign = TextAlign.Center
            )
        } else {
            CollapsingToolbarScaffold(modifier = Modifier.fillMaxSize(),
                state = collapsingScrollState,
                scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
                toolbar = {
                    DetailsAppBar(modifier = Modifier.fillMaxWidth(),
                        collapsingScrollState = collapsingScrollState,
                        movieDetails = movieDetailsState.movieDetails,
                        // isLoading = movieDetailsState.isLoading,
                        onNavigationIconClick = {
                            navController.navigateUp()
                        },
                        onShareIconClick = {
                            shareMovie(context = context, movieId = movieId)
                        },
                        onFavoriteIconClick = {
                            if (it.isFavourite != true) {
                                detailsViewModel.saveMovieDetails(movieDetails = it)
                            } else {
                                navController.navigateUp()
                                detailsViewModel.deleteFavouriteMovie(movieId = movieId)
                            }
                        })
                }) {
                Column(
                    modifier = Modifier
                        .padding(bottom = 20.dp)
                        .verticalScroll(state = scrollState),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    //region Movie Ratings
                    val voteAverage = movieDetailsState.movieDetails?.voteAverage
                    voteAverage?.let {
                        MovieRatingSection(
                            popularity = voteAverage.getPopularity(),
                            voteAverage = voteAverage.getRating(),
                            // isLoading = movieDetailsState.isLoading
                        )
                    }
                    //endregion

                    //region Movie Overview
                    movieDetailsState.movieDetails?.overview?.let {
                        Text(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            text = stringResource(R.string.overview),
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
                    //endregion

                    //region Movie Cast
                    movieDetailsState.movieCast?.let {
                        Text(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            text = stringResource(id = R.string.cast),
                            style = MaterialTheme.typography.h6,
                            fontSize = 20.sp
                        )

                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            items(items = it) { item ->
                                ItemMovieCast(
                                    actor = item,
                                    // isLoading = movieDetailsState.isLoading
                                )
                            }
                        }
                    }
                    //endregion

                    //region Similar Movies
                    movieDetailsState.similarMovies?.let {
                        Text(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            text = stringResource(id = R.string.similar_movies),
                            style = MaterialTheme.typography.h6,
                            fontSize = 20.sp,
                            color = MaterialTheme.colors.onSurface
                        )

                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            items(items = it) { movie ->
                                ItemSimilarMovies(
                                    movie = movie,
                                    // isLoading = movieDetailsState.isLoading
                                )
                            }
                        }
                    }
                    //endregion
                }
            }
        }
    }
}

private fun shareMovie(context: Context, movieId: Int) {
    val shareIntent = Intent()
    shareIntent.action = Intent.ACTION_SEND
    shareIntent.type = "text/plain"
    shareIntent.putExtra(Intent.EXTRA_TEXT, "https://com.vickikbt.notlfix/id?=$movieId")
    context.startActivity(
        Intent.createChooser(
            shareIntent, context.resources.getString(R.string.share)
        )
    )
}

package com.vickikbt.notflix.ui.screens.details

import android.content.Context
import android.content.Intent
import android.widget.Toast
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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.placeholder
import com.vickikbt.notflix.R
import com.vickikbt.notflix.ui.components.ItemMovieCast
import com.vickikbt.notflix.ui.components.ItemSimilarMovies
import com.vickikbt.notflix.ui.components.MovieRatingSection
import com.vickikbt.notflix.ui.components.app_bars.DetailsAppBar
import com.vickikbt.notflix.ui.theme.Gray
import com.vickikbt.shared.presentation.presenters.SharedDetailsPresenter
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
    detailsViewModel: SharedDetailsPresenter = get(),
    movieId: Int,
    cacheId: Int,
) {

    LaunchedEffect(key1 = detailsViewModel) {
        detailsViewModel.getMovieDetails(movieId)
        detailsViewModel.fetchSimilarMovies(movieId)
        detailsViewModel.getMovieCast(movieId)
    }

    val movieDetails = detailsViewModel.movieDetails.collectAsState().value
    val movieCast = detailsViewModel.movieCast.collectAsState().value
    val similarMovies = detailsViewModel.similarMovies.collectAsState().value
    val error = detailsViewModel.error.collectAsState().value

    val context = LocalContext.current

    val scrollState = rememberScrollState()
    val collapsingScrollState = rememberCollapsingToolbarScaffoldState()

    if (error.isNullOrEmpty()) {
        CollapsingToolbarScaffold(
            modifier = Modifier.fillMaxSize(),
            state = collapsingScrollState,
            scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
            toolbar = {
                DetailsAppBar(
                    modifier = Modifier.fillMaxWidth(),
                    collapsingScrollState = collapsingScrollState,
                    movieDetails = movieDetails,
                    onNavigationIconClick = {
                        navController.navigateUp()
                    },
                    onShareIconClick = {
                        shareMovie(context = context, movieId = movieId)
                    },
                    onFavoriteIconClick = {
                        // Add to favourites
                        Toast.makeText(
                            context,
                            "Added ${movieDetails?.title} to favourites",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                )
            }
        ) {

            Column(
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .verticalScroll(state = scrollState),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {

                //region Movie Ratings
                val voteAverage = movieDetails?.voteAverage
                MovieRatingSection(
                    popularity = voteAverage?.getPopularity(),
                    voteAverage = voteAverage?.getRating()
                )
                //endregion

                //region Movie Overview
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = stringResource(R.string.overview),
                    style = MaterialTheme.typography.h6,
                    fontSize = 20.sp,
                    color = MaterialTheme.colors.onSurface,
                )

                Text(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .placeholder(
                            visible = movieDetails?.overview.isNullOrEmpty(),
                            color = Gray,
                            highlight = PlaceholderHighlight.fade(highlightColor = Color.Gray)
                        ),
                    text = movieDetails?.overview ?: "",
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onSurface,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Start,
                    overflow = TextOverflow.Ellipsis,
                )
                //endregion

                //region Movie Cast
                movieCast?.actor?.let {
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
                            ItemMovieCast(actor = item)
                        }
                    }
                }
                //endregion

                //region Similar Movies
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
                    similarMovies?.let {
                        items(items = it) { movie ->
                            ItemSimilarMovies(movie = movie)
                        }
                    }
                }
                //endregion
            }
        }
    } else {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(text = error, modifier = Modifier.align(Alignment.Center))
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
            shareIntent,
            context.resources.getString(R.string.share)
        )
    )
}

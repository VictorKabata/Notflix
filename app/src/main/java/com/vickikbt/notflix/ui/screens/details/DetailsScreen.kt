package com.vickikbt.notflix.ui.screens.details

import android.content.Context
import android.content.Intent
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import com.vickikbt.domain.models.MovieDetails
import com.vickikbt.notflix.R
import com.vickikbt.notflix.ui.components.ItemMovieCast
import com.vickikbt.notflix.ui.components.ItemSimilarMovies
import com.vickikbt.notflix.ui.components.MovieRatingSection
import com.vickikbt.notflix.ui.components.app_bars.DetailsAppBar
import com.vickikbt.notflix.ui.theme.Gray
import com.vickikbt.notflix.ui.theme.TextSecondary
import com.vickikbt.notflix.util.getMovieDuration
import com.vickikbt.notflix.util.getPopularity
import com.vickikbt.notflix.util.getRating
import com.vickikbt.notflix.util.loadImage
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import timber.log.Timber
import kotlin.math.min

@Composable
fun DetailsScreen(
    navController: NavController,
    detailsViewModel: DetailsViewModel = getViewModel(),
    movieId: Int,
    cacheId: Int,
) {
    detailsViewModel.apply {
        getIsMovieFavorite(movieId)
        getMovieDetails(movieId)
        getMovieCast(movieId)
        getMovieVideo(movieId)
        fetchSimilarMovies(movieId)
    }

    val movieDetails = detailsViewModel.movieDetails.observeAsState().value
    val movieCast = detailsViewModel.movieCast.observeAsState().value
    val similarMovies = detailsViewModel.similarMovies.observeAsState().value
    val movieVideo = detailsViewModel.movieVideo.observeAsState().value
    val isMovieFavorite = detailsViewModel.movieIsFavorite.observeAsState().value

    Timber.e("Is movie fav: $isMovieFavorite")

    LaunchedEffect(key1 = Unit) {
        launch {
            if (movieDetails != null && movieCast != null) {
                Timber.e("Saving movie details")
                detailsViewModel.saveMovieDetails(
                    movieDetails = movieDetails,
                    cast = movieCast,
                    movieVideo = movieVideo
                )
            }
        }
    }
    val context = LocalContext.current

    val lazyListState = rememberLazyListState()
    val scrollOffset = min(
        1f.coerceAtMost(1f),
        (1 - (lazyListState.firstVisibleItemScrollOffset / 600f + lazyListState.firstVisibleItemIndex)).coerceAtLeast(
            0f
        )
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.surface
    ) {
        Box {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                state = lazyListState
            ) {

                //region Movie Poster
                item {
                    MoviePoster(
                        modifier = Modifier,
                        movieDetails = movieDetails,
                        scrollOffset = scrollOffset
                    )
                }
                //endregion

                //region Movie Ratings
                item {
                    val voteAverage = movieDetails?.voteAverage
                    MovieRatingSection(
                        popularity = voteAverage?.getPopularity(),
                        voteAverage = voteAverage?.getRating()
                    )
                }
                //endregion

                //region Movie Overview
                item {
                    Text(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        text = stringResource(R.string.overview),
                        style = MaterialTheme.typography.h6,
                        fontSize = 20.sp,
                        color = MaterialTheme.colors.onSurface,
                    )

                    Spacer(modifier = Modifier.height(8.dp))

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
                }
                //endregion

                //region Movie Cast
                if (!movieCast?.actor.isNullOrEmpty()) {
                    item {
                        Text(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            text = stringResource(id = R.string.cast),
                            style = MaterialTheme.typography.h6,
                            fontSize = 20.sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            items(items = movieCast?.actor!!) { item ->
                                ItemMovieCast(actor = item)
                            }
                        }
                    }
                }
                //endregion

                //region Movie Trailer
                item {
                    Text(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        text = stringResource(id = R.string.trailer),
                        style = MaterialTheme.typography.h6,
                        fontSize = 20.sp,
                        color = MaterialTheme.colors.onSurface
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Card(
                        modifier = Modifier
                            .padding(horizontal = 15.dp)
                            .fillMaxWidth()
                            .height(250.dp),
                        elevation = 12.dp,
                        shape = RoundedCornerShape(8.dp),
                        backgroundColor = TextSecondary
                    ) {
                        //ToDo: Card content
                    }
                }
                //endregion

                //region Similar Movies
                item {
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
                        if (similarMovies?.movies != null) {
                            items(similarMovies.movies!!) { movie ->
                                ItemSimilarMovies(movie = movie)
                            }
                        }
                    }
                }
                //endregion

            }

            //region App Bar
            DetailsAppBar(
                scrollOffset = scrollOffset,
                title = movieDetails?.title,
                onNavigationIconClick = { navController.navigateUp() },
                onShareIconClick = { shareMovie(context = context, movieId = movieId) },
                onFavoriteIconClick = {
                    if (isMovieFavorite != null && isMovieFavorite == true) {
                        updateMovieFavorite(
                            viewModel = detailsViewModel,
                            isFavorite = false,
                            cacheId = cacheId
                        )
                    } else if (isMovieFavorite != null && isMovieFavorite == false) {
                        updateMovieFavorite(
                            viewModel = detailsViewModel,
                            isFavorite = true,
                            cacheId = cacheId
                        )
                    }
                })
            //endregion
        }
    }
}

@Composable
fun MoviePoster(
    modifier: Modifier = Modifier,
    movieDetails: MovieDetails?,
    viewModel: DetailsViewModel = getViewModel(),
    scrollOffset: Float
) {
    val imageSize by animateDpAsState(
        targetValue = max(56.dp, 350.dp * scrollOffset),
        animationSpec = tween(easing = FastOutLinearInEasing)
    )

    val defaultDominantColor = MaterialTheme.colors.surface
    val defaultDominantTextColor = MaterialTheme.colors.onSurface
    var dominantColor by remember { mutableStateOf(defaultDominantColor) }
    var dominantTextColor by remember { mutableStateOf(defaultDominantTextColor) }

    val imagePainter = rememberImagePainter(data = movieDetails?.backdropPath?.loadImage())

    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .height(imageSize)
            .placeholder(
                visible = movieDetails == null,
                color = Gray,
                highlight = PlaceholderHighlight.shimmer(highlightColor = TextSecondary)
            )
    ) {

        val (imageMovie, boxFadingEdge, textViewRunTime, textViewTitle) = createRefs()

        //region Movie Poster
        if (imagePainter.state is ImagePainter.State.Success) {
            LaunchedEffect(key1 = imagePainter) {
                launch {
                    val imageDrawable =
                        imagePainter.imageLoader.execute(imagePainter.request).drawable
                    viewModel.getImagePalette(imageDrawable!!) {
                        dominantColor = Color(it.rgb)
                        dominantTextColor = Color(it.titleTextColor)
                    }
                }
            }
        }

        Image(
            painter = imagePainter,
            contentDescription = stringResource(R.string.movie_poster),
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer { alpha = scrollOffset }
                .constrainAs(imageMovie) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(210.dp)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            dominantColor.copy(alpha = scrollOffset)
                        )
                    )
                )
                .constrainAs(boxFadingEdge) {
                    bottom.linkTo(parent.bottom)
                }
        )
        //endregion

        //region Movie Duration
        Text(
            text = movieDetails?.runtime?.getMovieDuration() ?: "",
            color = dominantTextColor.copy(alpha = scrollOffset),
            style = MaterialTheme.typography.h5,
            fontSize = 15.sp,
            modifier = Modifier.constrainAs(textViewRunTime) {
                start.linkTo(textViewTitle.start)
                bottom.linkTo(textViewTitle.top)
            }
        )
        //endregion

        //region Movie Title
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(textViewTitle) {
                    width = Dimension.fillToConstraints
                    start.linkTo(parent.start, margin = 6.dp)
                    end.linkTo(parent.end, margin = 6.dp)
                    bottom.linkTo(parent.bottom, margin = 10.dp)
                },
            text = movieDetails?.title ?: stringResource(R.string.unknown_movie),
            style = MaterialTheme.typography.h6,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = dominantTextColor.copy(alpha = (scrollOffset + 0.2f).coerceAtMost(1f)),
            fontSize = 30.sp
        )
        //endregion
    }
}

private fun updateMovieFavorite(isFavorite: Boolean, cacheId: Int, viewModel: DetailsViewModel) =
    viewModel.updateFavorite(cacheId = cacheId, isFavorite = isFavorite)

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


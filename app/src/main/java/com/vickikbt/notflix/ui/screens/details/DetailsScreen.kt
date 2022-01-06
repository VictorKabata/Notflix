package com.vickikbt.notflix.ui.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.vickikbt.notflix.ui.components.CastSection
import com.vickikbt.notflix.ui.components.MovieRatingSection
import com.vickikbt.notflix.ui.components.SimilarMoviesSection
import com.vickikbt.notflix.ui.components.TrailerSection
import com.vickikbt.notflix.ui.screens.home.HomeViewModel
import com.vickikbt.notflix.ui.theme.DarkTextPrimary
import com.vickikbt.notflix.ui.theme.TextSecondary
import com.vickikbt.notflix.util.loadImage
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun DetailsScreen(
    navController: NavController,
    detailsViewModel: DetailsViewModel = getViewModel(),
    movieId: Int = 791373,
    homeViewModel: HomeViewModel = getViewModel()
) {
    val defaultDominantColor = MaterialTheme.colors.surface
    val defaultDominantTextColor = MaterialTheme.colors.onSurface
    val dominantColor = remember { mutableStateOf(defaultDominantColor) }
    val dominantTextColor = remember { mutableStateOf(defaultDominantTextColor) }
    detailsViewModel.apply {
        fetchMovieCast(791373)
        fetchMovieDetails(791373)
        fetchMovieVideo(791373)
        fetchSimilarMovies(791373)
    }
    val movieDetails = detailsViewModel.movieDetails.observeAsState().value
    val movieCast = detailsViewModel.movieCast.observeAsState().value
    val similarMovies = detailsViewModel.similarMovies.observeAsState().value
    val movieVideo = detailsViewModel.movieVideo.observeAsState().value
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        val (movieTitle, movieImage, movieDateTime, backArrow, favIcon, similarMoviesSection, overViewSection, castSection, boxFadingEdge, ratingSection, trailerSection) = createRefs()

        val imagePainter =
            detailsPainter(pictureUrl = "https://image.tmdb.org/t/p/w500${movieDetails?.backdropPath}")

        val movieYear = movieDetails?.releaseDate?.take(4)
        val hourTime = (movieDetails?.runtime)?.div(60)
        val minutes = (movieDetails?.runtime)?.rem(60)

        // Movie image region
        Image(
            painter = imagePainter,
            contentDescription = "movie image",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .constrainAs(movieImage) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            contentScale = ContentScale.Crop
        )

        if (imagePainter.state is ImagePainter.State.Success) {
            LaunchedEffect(key1 = imagePainter) {
                launch {
                    val imageDrawable =
                        imagePainter.imageLoader.execute(imagePainter.request).drawable
                    homeViewModel.getImagePalette(imageDrawable!!) {
                        dominantColor.value = Color(it.rgb)
                        dominantTextColor.value = Color(it.titleTextColor)
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            dominantColor.value
                        )
                    )
                )
                .constrainAs(boxFadingEdge) {
                    top.linkTo(movieImage.top)
                    bottom.linkTo(movieImage.bottom)
                    height = Dimension.fillToConstraints
                }
        )

        // Movie title
        Text(
            text = movieDetails?.title ?: "unknown movie",
            style = MaterialTheme.typography.h6.copy(fontSize = 30.sp),
            modifier = Modifier
                .constrainAs(movieTitle) {
                    start.linkTo(parent.start, margin = 5.dp)
                    bottom.linkTo(movieImage.bottom, margin = 10.dp)
                }
                .fillMaxWidth(),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = dominantTextColor.value,
        )

        // Movie date and time
        Text(
            text = "$movieYear | ${hourTime}hrs:${minutes}mins",
            color = dominantTextColor.value,
            style = MaterialTheme.typography.h6.copy(fontSize = 15.sp),
            modifier = Modifier.constrainAs(movieDateTime) {
                start.linkTo(movieTitle.start)
                bottom.linkTo(movieTitle.top)
            }
        )

        val moviePopularity = movieDetails?.voteCount
        val voteAverage = movieDetails?.voteAverage

        if (moviePopularity != null && voteAverage != null) {
            MovieRatingSection(
                popularity = moviePopularity,
                voteAverage = voteAverage,
                modifier = Modifier.constrainAs(ratingSection) {
                    top.linkTo(movieImage.bottom, margin = 15.dp)
                }
            )
        }

        if (movieDetails?.overview != null) {
            MovieOverview(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(overViewSection) {
                        top.linkTo(ratingSection.bottom, margin = 20.dp)
                    },
                overview = movieDetails.overview!!
            )
        }

        CastSection(
            modifier = Modifier.constrainAs(castSection) {
                top.linkTo(overViewSection.bottom, margin = 10.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end, margin = 10.dp)
                width = Dimension.fillToConstraints
            },
            cast = movieCast
        )

        TrailerSection(
            modifier = Modifier.constrainAs(trailerSection) {
                top.linkTo(castSection.bottom, margin = 30.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
        )

        SimilarMoviesSection(
            similarMovies = similarMovies,
            modifier = Modifier.constrainAs(similarMoviesSection) {
                top.linkTo(trailerSection.bottom, margin = 10.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
    }
}

@Composable
fun detailsPainter(pictureUrl: String?): ImagePainter {
    return rememberImagePainter(data = pictureUrl?.loadImage())
}

@Composable
fun MovieOverview(modifier: Modifier, overview: String) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        val (title, overviewText) = createRefs()
        // title of the section
        Text(
            text = "Overview",
            style = MaterialTheme.typography.h6,
            fontSize = 20.sp,
            modifier = Modifier.constrainAs(title) {
                start.linkTo(parent.start, margin = 10.dp)
                end.linkTo(parent.end, margin = 10.dp)
                width = Dimension.fillToConstraints
                top.linkTo(parent.top)
            },
            color = DarkTextPrimary
        )

        // Movie overview text
        Text(
            text = overview,
            style = MaterialTheme.typography.body1.copy(color = TextSecondary),
            modifier = Modifier.constrainAs(overviewText) {
                top.linkTo(title.bottom, margin = 5.dp)
                start.linkTo(parent.start, margin = 10.dp)
                width = Dimension.fillToConstraints
                end.linkTo(parent.end, margin = 10.dp)
            },
            textAlign = TextAlign.Start,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

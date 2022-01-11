package com.vickikbt.notflix.ui.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
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
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import com.vickikbt.domain.models.MovieDetails
import com.vickikbt.notflix.ui.components.CastSection
import com.vickikbt.notflix.ui.components.MovieRatingSection
import com.vickikbt.notflix.ui.components.SimilarMoviesSection
import com.vickikbt.notflix.ui.components.TrailerSection
import com.vickikbt.notflix.ui.theme.DarkTextPrimary
import com.vickikbt.notflix.ui.theme.Gray
import com.vickikbt.notflix.ui.theme.TextSecondary
import com.vickikbt.notflix.util.getMovieDuration
import com.vickikbt.notflix.util.getPopularity
import com.vickikbt.notflix.util.getRating
import com.vickikbt.notflix.util.loadImage
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun DetailsScreen(
    navController: NavController,
    detailsViewModel: DetailsViewModel = getViewModel(),
    movieId: Int,
) {
    detailsViewModel.apply {
        fetchMovieCast(movieId)
        fetchMovieDetails(movieId)
        fetchMovieVideo(movieId)
        fetchSimilarMovies(movieId)
    }
    val movieDetails = detailsViewModel.movieDetails.observeAsState().value
    val movieCast = detailsViewModel.movieCast.observeAsState().value
    val similarMovies = detailsViewModel.similarMovies.observeAsState().value
    val movieVideo = detailsViewModel.movieVideo.observeAsState().value
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .placeholder(
                    visible = false,
                    color = MaterialTheme.colors.primary,
                    highlight = PlaceholderHighlight.shimmer(highlightColor = Color.Gray)
                ),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            MovieImage(movieDetails)

            val voteAverage = movieDetails?.voteAverage

            MovieRatingSection(
                popularity = voteAverage?.getPopularity(),
                voteAverage = voteAverage?.getRating(),
                modifier = Modifier
            )

            MovieOverview(
                modifier = Modifier
                    .fillMaxWidth(),
                overview = movieDetails?.overview
            )

            CastSection(
                modifier = Modifier,
                cast = movieCast
            )

            if (movieDetails?.video == true) {
                TrailerSection(
                    modifier = Modifier,
                )
            }

            SimilarMoviesSection(
                similarMovies = similarMovies,
                modifier = Modifier
            )
        }
    }
}

@Composable
fun MovieImage(movieDetails: MovieDetails?, viewModel: DetailsViewModel = getViewModel()) {
    val defaultDominantColor = MaterialTheme.colors.surface
    val defaultDominantTextColor = MaterialTheme.colors.onSurface
    val dominantColor = remember { mutableStateOf(defaultDominantColor) }
    val dominantTextColor = remember { mutableStateOf(defaultDominantTextColor) }
    ConstraintLayout(
        modifier = Modifier.placeholder(
            visible = movieDetails == null,
            color = Gray,
            highlight = PlaceholderHighlight.shimmer(highlightColor = TextSecondary)
        )
    ) {

        val (movieImage, runTime, movieTitle) = createRefs()
        val imagePainter = rememberImagePainter(data = movieDetails?.backdropPath?.loadImage())
        val movieRunTime = movieDetails?.runtime?.getMovieDuration()

        // Movie image region

        if (imagePainter.state is ImagePainter.State.Success) {
            LaunchedEffect(key1 = imagePainter) {
                launch {
                    val imageDrawable =
                        imagePainter.imageLoader.execute(imagePainter.request).drawable
                    viewModel.getImagePalette(imageDrawable!!) {
                        dominantColor.value = Color(it.rgb)
                        dominantTextColor.value = Color(it.titleTextColor)
                    }
                }
            }
        }

        Image(
            painter = imagePainter,
            contentDescription = "movie image",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .drawWithCache {
                    val gradient = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, dominantColor.value),
                        startY = size.height / 9,
                        endY = size.height
                    )
                    onDrawWithContent {
                        drawContent()
                        drawRect(gradient, blendMode = BlendMode.Multiply)
                    }
                }
                .constrainAs(movieImage) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            contentScale = ContentScale.Crop
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
            text = "$movieRunTime",
            color = dominantTextColor.value,
            style = MaterialTheme.typography.h6.copy(fontSize = 15.sp),
            modifier = Modifier.constrainAs(runTime) {
                start.linkTo(movieTitle.start)
                bottom.linkTo(movieTitle.top)
            }
        )
    }
}

@Composable
fun MovieOverview(modifier: Modifier, overview: String?) {
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
            modifier = Modifier
                .constrainAs(title) {
                    start.linkTo(parent.start, margin = 10.dp)
                    end.linkTo(parent.end, margin = 10.dp)
                    width = Dimension.fillToConstraints
                    top.linkTo(parent.top)
                },
            color = DarkTextPrimary
        )

        // Movie overview text
        Text(
            text = overview ?: "",
            style = MaterialTheme.typography.body1.copy(color = TextSecondary),
            modifier = Modifier
                .constrainAs(overviewText) {
                    top.linkTo(title.bottom, margin = 5.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                    width = Dimension.fillToConstraints
                    end.linkTo(parent.end, margin = 10.dp)
                }
                .placeholder(
                    visible = overview.isNullOrEmpty(),
                    color = Gray,
                    highlight = PlaceholderHighlight.shimmer(highlightColor = Color.Gray)
                ),
            textAlign = TextAlign.Start,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

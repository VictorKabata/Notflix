package com.vickikbt.notflix.ui.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.vickikbt.notflix.ui.components.NotflixText
import org.koin.androidx.compose.getViewModel

@Composable
fun DetailsScreen(
    navController: NavController,
    detailsViewModel: DetailsViewModel = getViewModel(),
    movieId: Int
) {
    with(movieId) {
        detailsViewModel.apply {
            fetchMovieCast(this@with)
            fetchMovieDetails(this@with)
            fetchMovieVideo(this@with)
            fetchSimilarMovies(this@with)
        }
    }
    val movieDetails = detailsViewModel.movieDetails.observeAsState().value
    val movieCast = detailsViewModel.movieCast.observeAsState().value
    val similarMovies = detailsViewModel.similarMovies.observeAsState().value
    val movieVideo = detailsViewModel.movieVideo.observeAsState().value
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {

        val (movieTitle, movieImage, movieDateTime, backArrow, favIcon, similarMoviesSection, overViewSection, castSection) = createRefs()

        if (movieDetails != null) {
            Image(
                painter = detailsPainter(pictureUrl = movieDetails.posterPath),
                contentDescription = "movie image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .constrainAs(movieImage) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )

            NotflixText(
                text = movieDetails.title!!,
                style = MaterialTheme.typography.h2,
                modifier = Modifier.constrainAs(movieTitle) {
                    start.linkTo(parent.start, margin = 5.dp)
                    bottom.linkTo(movieImage.bottom, margin = 10.dp)
                }
            )
        }
    }
}

@Composable
fun detailsPainter(pictureUrl: String?): Painter {
    return rememberImagePainter(data = pictureUrl)
}

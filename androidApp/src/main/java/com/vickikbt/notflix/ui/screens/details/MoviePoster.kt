package com.vickikbt.notflix.ui.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.vickikbt.notflix.R
import com.vickikbt.notflix.util.PaletteGenerator
import com.vickikbt.notflix.util.getMovieDuration
import com.vickikbt.notflix.util.loadImage
import com.vickikbt.shared.domain.models.MovieDetails

@ExperimentalCoilApi
@Composable
fun MoviePoster(
    modifier: Modifier = Modifier,
    movieDetails: MovieDetails?
) {
    val defaultDominantColor = MaterialTheme.colors.surface
    val defaultDominantTextColor = MaterialTheme.colors.onSurface
    var dominantColor by remember { mutableStateOf(defaultDominantColor) }
    var dominantTextColor by remember { mutableStateOf(defaultDominantTextColor) }

    val painter = rememberImagePainter(data = movieDetails?.backdropPath?.loadImage())

    if (painter.state is ImagePainter.State.Success) {
        LaunchedEffect(key1 = painter) {
            val imageDrawable = painter.imageLoader.execute(painter.request).drawable
            imageDrawable?.let {
                PaletteGenerator.generateImagePalette(imageDrawable = it) { color ->
                    dominantColor = Color(color.rgb)
                    dominantTextColor = Color(color.titleTextColor)
                }
            }
        }
    }

    ConstraintLayout(modifier = modifier) {

        val (imageMovie, boxFadingEdge, textViewRunTime, textViewTitle) = createRefs()

        //region Movie Poster
        Image(
            painter = painter,
            contentDescription = stringResource(R.string.movie_poster),
            modifier = Modifier
                .fillMaxSize()
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
                            dominantColor
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
            color = dominantTextColor,
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
            color = dominantTextColor,
            fontSize = 30.sp
        )
        //endregion
    }
}

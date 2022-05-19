package com.vickikbt.notflix.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle
import com.gowtham.ratingbar.StepSize
import com.vickikbt.shared.domain.models.Movie
import com.vickikbt.notflix.ui.screens.home.HomeViewModel
import com.vickikbt.notflix.ui.theme.Black
import com.vickikbt.notflix.ui.theme.Golden
import com.vickikbt.notflix.util.getRating
import com.vickikbt.notflix.util.loadImage
import kotlinx.coroutines.launch

@Composable
fun ItemNowPlayingMovies(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    movie: Movie,
    onItemClick: () -> Unit
) {
    val defaultDominantColor = MaterialTheme.colors.surface
    val defaultDominantTextColor = MaterialTheme.colors.onSurface
    val dominantColor = remember { mutableStateOf(defaultDominantColor) }
    val dominantTextColor = remember { mutableStateOf(defaultDominantTextColor) }

    Box(modifier = modifier.clickable { onItemClick() }) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (imageMovieCover, boxFadingEdge, textMovieTitle, ratingBarRanking) = createRefs()

            val painter = rememberImagePainter(
                data = movie.backdropPath?.loadImage(LocalContext.current),
                builder = { crossfade(true) }
            )

            //region Movie Cover Image
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .constrainAs(imageMovieCover) {},
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
                painter = painter,
                contentDescription = null
            )
            //endregion

            //region Fading Edge Box
            if (painter.state is ImagePainter.State.Success) {
                LaunchedEffect(key1 = painter) {
                    launch {
                        val imageDrawable = painter.imageLoader.execute(painter.request).drawable
                        viewModel.getImagePalette(imageDrawable!!) {
                            dominantColor.value = Color(it.rgb)
                            dominantTextColor.value = Color(it.titleTextColor)
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(210.dp)
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                dominantColor.value
                            )
                        )
                    )
                    .constrainAs(boxFadingEdge) {
                        bottom.linkTo(parent.bottom)
                    }
            )
            //endregion

            //region Movie Title
            Text(
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp)
                    .constrainAs(textMovieTitle) {
                        width = Dimension.fillToConstraints
                        start.linkTo(parent.start)
                        bottom.linkTo(ratingBarRanking.top)
                        end.linkTo(parent.end)
                    },
                text = movie.title ?: "Unknown movie",
                fontSize = 32.sp,
                maxLines = 2,
                style = MaterialTheme.typography.h6,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
                color = dominantTextColor.value
            )
            //endregion

            //region Movie Rating
            RatingBar(
                modifier = Modifier
                    .padding(start = 8.dp, bottom = 6.dp)
                    .constrainAs(ratingBarRanking) {
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                    },
                value = movie.voteAverage?.getRating() ?: 0f,
                numStars = 5,
                size = 18.dp,
                stepSize = StepSize.HALF,
                isIndicator = true,
                ratingBarStyle = RatingBarStyle.Normal,
                activeColor = Golden,
                inactiveColor = Black,
                onValueChange = {},
                onRatingChanged = {}
            )
            //endregion
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
}

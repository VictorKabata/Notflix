package com.vickikbt.notflix.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.shimmer
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle
import com.gowtham.ratingbar.StepSize
import com.vickikbt.notflix.ui.screens.home.HomeViewModel
import com.vickikbt.notflix.ui.theme.Black
import com.vickikbt.notflix.ui.theme.Golden
import com.vickikbt.notflix.util.PaletteGenerator
import com.vickikbt.notflix.util.getRating
import com.vickikbt.notflix.util.loadImage
import com.vickikbt.shared.domain.models.Movie
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

@Composable
fun ItemNowPlayingMovies(
    modifier: Modifier = Modifier,
    movie: Movie,
    onItemClick: () -> Unit
) {
    val defaultDominantColor = MaterialTheme.colors.surface
    val defaultDominantTextColor = MaterialTheme.colors.onSurface
    var dominantColor by remember { mutableStateOf(defaultDominantColor) }
    var dominantTextColor by remember { mutableStateOf(defaultDominantTextColor) }

    Box(modifier = modifier.clickable { onItemClick() }) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (imageMovieCover, boxFadingEdge, textMovieTitle, ratingBarRanking) = createRefs()

            val painter = rememberImagePainter(
                data = movie.backdropPath?.loadImage(),
                builder = {
                    crossfade(true)
                }
            )

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

            //region Movie Cover Image
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .constrainAs(imageMovieCover) {}
                    .placeholder(
                        visible = false,
                        color = Color.Gray,
                        highlight = PlaceholderHighlight.shimmer(highlightColor = Color.White)
                    ),
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
                painter = painter,
                contentDescription = null
            )
            //endregion

            //region Fading Edge Box
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
                color = dominantTextColor
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

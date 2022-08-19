package com.vickikbt.notflix.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.material.placeholder
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle
import com.gowtham.ratingbar.StepSize
import com.vickikbt.notflix.ui.theme.Golden
import com.vickikbt.notflix.ui.theme.Gray
import com.vickikbt.notflix.util.PaletteGenerator
import com.vickikbt.notflix.util.loadImage
import com.vickikbt.shared.domain.models.Movie
import com.vickikbt.shared.domain.utils.capitalizeEachWord
import com.vickikbt.shared.domain.utils.getRating
import com.vickikbt.shared.domain.utils.getReleaseDate

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun ItemPopularMovies(
    modifier: Modifier = Modifier,
    movie: Movie,
    onClickItem: (Movie) -> Unit
) {
    val defaultDominantTextColor = MaterialTheme.colors.onSurface
    var dominantColor by remember { mutableStateOf(Color.Transparent) }
    var dominantTextColor by remember { mutableStateOf(defaultDominantTextColor) }
    var dominantSubTextColor by remember { mutableStateOf(defaultDominantTextColor) }

    val painter = rememberImagePainter(
        data = movie.backdropPath?.loadImage(),
        builder = { crossfade(true) }
    )

    if (painter.state is ImagePainter.State.Success) {
        LaunchedEffect(key1 = painter) {
            val imageDrawable = painter.imageLoader.execute(painter.request).drawable
            imageDrawable?.let {
                PaletteGenerator.generateImagePalette(imageDrawable = it) { color ->
                    dominantColor = Color(color.rgb)
                    dominantTextColor = Color(color.titleTextColor)
                    dominantSubTextColor = Color(color.bodyTextColor)
                }
            }
        }
    }

    Card(
        modifier = modifier
            .clickable { onClickItem(movie) }
            .placeholder(
                visible = false,
                color = Color.Black,
                highlight = PlaceholderHighlight.fade()
            ),
        elevation = 8.dp,
        shape = RoundedCornerShape(4.dp)
    ) {

        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (imageMovieCover, boxFadingEdge, textMovieTitle, rowRankRelease) = createRefs()

            //region Movie Cover
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .placeholder(
                        visible = false,
                        color = Color.Black,
                        highlight = PlaceholderHighlight.fade()
                    )
                    .background(color = Gray)
                    .constrainAs(imageMovieCover) {},
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
                painter = painter,
                contentDescription = null
            )
            //endregion

            //region Fading Edge
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
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
                        bottom.linkTo(rowRankRelease.top)
                        end.linkTo(parent.end)
                    },
                text = movie.title ?: "Unknown movie",
                fontSize = 18.sp,
                maxLines = 2,
                style = MaterialTheme.typography.h6,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
                color = dominantTextColor
            )
            //endregion

            //region Movie Rating
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .constrainAs(rowRankRelease) {
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                    },
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {

                RatingBar(
                    modifier = Modifier,
                    value = movie.voteAverage?.getRating()?.toFloat() ?: 0f,
                    numStars = 5,
                    size = 15.dp,
                    stepSize = StepSize.HALF,
                    isIndicator = true,
                    ratingBarStyle = RatingBarStyle.Normal,
                    activeColor = Golden,
                    inactiveColor = Gray,
                    onValueChange = {},
                    onRatingChanged = {}
                )

                if (!movie.releaseDate.isNullOrEmpty()) {
                    Divider(
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .width(1.dp)
                            .height(13.dp),
                        color = dominantSubTextColor,
                    )

                    Text(
                        modifier = Modifier,
                        text = movie.releaseDate.getReleaseDate()?.capitalizeEachWord()!!,
                        fontSize = 14.sp,
                        maxLines = 1,
                        style = MaterialTheme.typography.h4,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Start,
                        color = dominantSubTextColor
                    )
                }
            }
            //endregion
        }
    }
}

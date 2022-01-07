package com.vickikbt.notflix.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle
import com.gowtham.ratingbar.StepSize
import com.vickikbt.domain.models.Movie
import com.vickikbt.notflix.ui.screens.home.HomeViewModel
import com.vickikbt.notflix.ui.theme.Golden
import com.vickikbt.notflix.ui.theme.Gray
import com.vickikbt.notflix.util.getRating
import com.vickikbt.notflix.util.getReleaseDate
import com.vickikbt.notflix.util.loadImage
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun ItemPopularMovies(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    movie: Movie,
    onClickItem: (Movie) -> Unit
) {
    val defaultDominantColor = MaterialTheme.colors.surface
    val defaultDominantTextColor = MaterialTheme.colors.onSurface
    val dominantColor = remember { mutableStateOf(defaultDominantColor) }
    val dominantTextColor = remember { mutableStateOf(defaultDominantTextColor) }
    val dominantSubTextColor = remember { mutableStateOf(defaultDominantTextColor) }

    Card(
        modifier = modifier
            .width(300.dp)
            .height(210.dp)
            .clickable { onClickItem(movie) },
        elevation = 8.dp,
        shape = RoundedCornerShape(4.dp)
    ) {

        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (imageMovieCover, boxFadingEdge, textMovieTitle, rowRankRelease) = createRefs()

            val painter = rememberImagePainter(
                data = movie.backdropPath?.loadImage(),
                builder = { crossfade(true) }
            )

            //region Movie Cover
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

            //region Fading Edge
            if (painter.state is ImagePainter.State.Success) {
                LaunchedEffect(key1 = painter) {
                    launch {
                        val imageDrawable = painter.imageLoader.execute(painter.request).drawable
                        viewModel.getImagePalette(imageDrawable!!) {
                            dominantColor.value = Color(it.rgb)
                            dominantTextColor.value = Color(it.titleTextColor)
                            dominantSubTextColor.value = Color(it.bodyTextColor)
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
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
                        bottom.linkTo(rowRankRelease.top)
                        end.linkTo(parent.end)
                    },
                text = movie.title ?: "Unknown movie",
                fontSize = 18.sp,
                maxLines = 2,
                style = MaterialTheme.typography.h6,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
                color = dominantTextColor.value
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
                    value = movie.voteAverage?.getRating() ?: 0f,
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
                            .width(2.dp)
                            .height(13.dp),
                        color = Gray.copy(alpha = .4f),
                    )

                    Text(
                        modifier = Modifier,
                        text = movie.releaseDate!!.getReleaseDate(),
                        fontSize = 14.sp,
                        maxLines = 1,
                        style = MaterialTheme.typography.h5,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Start,
                        color = dominantSubTextColor.value
                    )
                }
            }
            //endregion
        }
    }
}

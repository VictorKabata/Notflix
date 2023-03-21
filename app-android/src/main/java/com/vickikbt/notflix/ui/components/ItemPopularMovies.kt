package com.vickikbt.notflix.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.google.accompanist.placeholder.material.placeholder
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle
import com.gowtham.ratingbar.StepSize
import com.vickikbt.notflix.ui.theme.Golden
import com.vickikbt.notflix.ui.theme.Gray
import com.vickikbt.notflix.util.PaletteGenerator
import com.vickikbt.notflix.util.loadImage
import com.vickikbt.shared.domain.models.Movie
import com.vickikbt.shared.utils.capitalizeEachWord
import com.vickikbt.shared.utils.getRating
import com.vickikbt.shared.utils.getReleaseDate

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun ItemPopularMovies(
    modifier: Modifier = Modifier,
    movie: Movie,
    isLoading: Boolean = true,
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
        modifier = modifier.clickable { onClickItem(movie) },
        elevation = 8.dp,
        shape = RoundedCornerShape(4.dp)
    ) {
        Box(modifier = modifier) {
            //region Movie Cover
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .placeholder(
                        visible = isLoading,
                        color = Color.Gray.copy(alpha = .8f),
                        shape = RoundedCornerShape(4.dp)
                    )
                    .align(Alignment.Center),
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
                                if (isLoading) Color.Transparent else dominantColor
                            )
                        )
                    )
                    .align(Alignment.BottomCenter)
            )
            //endregion

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .align(Alignment.BottomCenter)
            ) {
                //region Movie Title
                Text(
                    modifier = Modifier.placeholder(
                        visible = isLoading,
                        color = Color.Gray
                    ),
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
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RatingBar(
                        modifier = Modifier.placeholder(
                            visible = isLoading,
                            color = Color.Gray,
                            shape = RoundedCornerShape(0.dp)
                        ),
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

                    movie.releaseDate?.let {
                        Divider(
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                                .width(1.dp)
                                .height(13.dp),
                            color = dominantSubTextColor,
                        )

                        Text(
                            modifier = Modifier.placeholder(
                                visible = isLoading,
                                color = Color.Gray,
                                shape = RoundedCornerShape(0.dp)
                            ),
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
}

package com.vickikbt.shared.presentation.ui.components

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.vickikbt.shared.domain.models.Movie

@ExperimentalMaterialApi
@Composable
fun ItemPopularMovies(
    modifier: Modifier = Modifier,
    movie: Movie,
    isLoading: Boolean = false,
    onClickItem: (Movie) -> Unit
) {
    val defaultDominantTextColor = MaterialTheme.colors.onSurface
    var dominantColor by remember { mutableStateOf(Color.Transparent) }
    var dominantTextColor by remember { mutableStateOf(defaultDominantTextColor) }
    var dominantSubTextColor by remember { mutableStateOf(defaultDominantTextColor) }

    /*val painter = rememberImagePainter(
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
    }*/
}

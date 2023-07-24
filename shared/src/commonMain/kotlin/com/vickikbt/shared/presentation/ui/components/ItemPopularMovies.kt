package com.vickikbt.shared.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seiko.imageloader.rememberAsyncImagePainter
import com.vickikbt.shared.domain.models.Movie
import com.vickikbt.shared.utils.capitalizeEachWord
import com.vickikbt.shared.utils.getReleaseDate
import com.vickikbt.shared.utils.loadImage

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
                    .align(Alignment.Center),
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
                painter = rememberAsyncImagePainter(movie.backdropPath?.loadImage() ?: ""),
                contentDescription = null
            )
            //endregion

            //region Fading Edge
            /* ToDo: Implement fading edge
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
            )*/
            //endregion

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .align(Alignment.BottomCenter)
            ) {
                //region Movie Title
                Text(
                    modifier = Modifier,
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
                    /* ToDo: Rating bar
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
                    )*/

                    movie.releaseDate?.let {
                        /*Divider(
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                                .width(1.dp)
                                .height(13.dp),
                            color = dominantSubTextColor,
                        )*/

                        Text(
                            modifier = Modifier,
                            text = movie.releaseDate.getReleaseDate()?.capitalizeEachWord() ?: "",
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

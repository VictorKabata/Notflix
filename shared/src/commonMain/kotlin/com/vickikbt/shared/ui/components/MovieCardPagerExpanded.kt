package com.vickikbt.shared.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seiko.imageloader.rememberImagePainter
import com.vickikbt.shared.domain.models.Movie
import com.vickikbt.shared.ui.components.ratingbar.RatingBar
import com.vickikbt.shared.ui.components.ratingbar.RatingBarStyle
import com.vickikbt.shared.ui.components.ratingbar.StepSize
import com.vickikbt.shared.utils.commonImageLoader
import com.vickikbt.shared.utils.getRating
import com.vickikbt.shared.utils.loadImage

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MovieCardPagerExpanded(
    modifier: Modifier = Modifier,
    movie: Movie,
    onItemClick: (Movie) -> Unit
) {
    var dominantColor by remember { mutableStateOf(Color.DarkGray) }
    var dominantTextColor by remember { mutableStateOf(Color.LightGray) }

    var isHovered by remember { mutableStateOf(false) }

    Card(modifier = modifier.clickable { onItemClick(movie) }
        .onPointerEvent(PointerEventType.Enter) {
            isHovered = true
        }.onPointerEvent(PointerEventType.Exit) {
            isHovered = false
        }
    ) {
        Box {
            //region Movie Cover Image
            commonImageLoader {
                val painter = rememberImagePainter(
                    if (isHovered) movie.backdropPath?.loadImage()
                        ?: "" else movie.posterPath?.loadImage() ?: ""
                )

                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center),
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Crop,
                    painter = painter,
                    contentDescription = null
                )
            }
            //endregion

            //region Fading Edge Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(190.dp)
                    .align(Alignment.BottomCenter)
                    .background(
                        Brush.verticalGradient(
                            listOf(Color.Transparent, dominantColor)
                        )
                    )
            )
            //endregion

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp)
                    .align(Alignment.BottomCenter),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    modifier = Modifier,
                    text = movie.title ?: "Unknown movie",
                    fontSize = 28.sp,
                    maxLines = 2,
                    style = MaterialTheme.typography.titleMedium,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start,
                    color = dominantTextColor,
                    lineHeight = 30.sp
                )

                movie.voteAverage?.let {
                    RatingBar(
                        modifier = Modifier,
                        value = it.getRating().toFloat(),
                        numOfStars = 5,
                        size = 13.dp,
                        spaceBetween = 1.dp,
                        stepSize = StepSize.HALF,
                        isIndicator = true,
                        style = RatingBarStyle.Fill()
                    )
                }
            }
        }
    }

}

package com.vickikbt.shared.presentation.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seiko.imageloader.rememberAsyncImagePainter
import com.vickikbt.shared.domain.models.Movie
import com.vickikbt.shared.presentation.ui.components.ratingbar.RatingBar
import com.vickikbt.shared.presentation.ui.components.ratingbar.RatingBarStyle
import com.vickikbt.shared.presentation.ui.components.ratingbar.StepSize
import com.vickikbt.shared.utils.getRating
import com.vickikbt.shared.utils.loadImage

@Composable
fun MovieCardPager(
    modifier: Modifier = Modifier,
    movie: Movie,
    onItemClick: (Movie) -> Unit
) {
    var dominantColor by remember { mutableStateOf(Color.DarkGray) }
    var dominantTextColor by remember { mutableStateOf(Color.LightGray) }

    val painter = rememberAsyncImagePainter(movie.backdropPath?.loadImage() ?: "")

    Box(modifier = modifier.clickable { onItemClick(movie) }) {
        //region Movie Cover Image
        Image(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
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
                fontSize = 32.sp,
                maxLines = 2,
                style = MaterialTheme.typography.h6,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
                color = dominantTextColor
            )

            movie.voteAverage?.let {
                RatingBar(
                    modifier = Modifier,
                    value = it.getRating().toFloat(),
                    numOfStars = 5,
                    size = 15.dp,
                    spaceBetween = 1.dp,
                    stepSize = StepSize.HALF,
                    isIndicator = true,
                    style = RatingBarStyle.Fill()
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieCardPagerIndicator(
    state: PagerState,
    pageCount: Int,
    modifier: Modifier = Modifier,
    indicatorSize: Dp = 6.dp,
    spacing: Dp = 6.dp,
    inactiveColor: Color = Color.LightGray,
    activeColor: Color = Color.DarkGray
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(
            space = spacing,
            alignment = Alignment.CenterHorizontally
        )
    ) {
        repeat(pageCount) { currentPage ->
            Canvas(modifier = Modifier.size(indicatorSize), onDraw = {
                drawCircle(
                    color = if (state.currentPage == currentPage) activeColor
                    else inactiveColor
                )
            })
        }
    }
}

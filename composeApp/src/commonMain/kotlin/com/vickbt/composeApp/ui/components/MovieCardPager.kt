package com.vickbt.composeApp.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import coil3.compose.AsyncImage
import com.kmpalette.loader.NetworkLoader
import com.kmpalette.loader.rememberNetworkLoader
import com.kmpalette.rememberDominantColorState
import com.vickbt.composeApp.ui.components.ratingbar.RatingBar
import com.vickbt.composeApp.ui.components.ratingbar.RatingBarStyle
import com.vickbt.composeApp.ui.components.ratingbar.StepSize
import com.vickbt.composeApp.utils.getRating
import com.vickbt.composeApp.utils.loadImage
import com.vickbt.shared.resources.Res
import com.vickbt.shared.resources.unknown_movie
import io.ktor.http.Url
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.jetbrains.compose.resources.stringResource

@Composable
fun MovieCardPager(
    modifier: Modifier = Modifier,
    movieId: Int,
    backdropPath: String? = null,
    title: String? = null,
    voteAverage: Double? = null,
    networkLoader: NetworkLoader = rememberNetworkLoader(),
    onItemClick: (Int) -> Unit
) {
    val dominantColorState = rememberDominantColorState(
        loader = networkLoader,
        defaultColor = Color.DarkGray,
        defaultOnColor = Color.LightGray,
        coroutineContext = Dispatchers.IO
    )

    backdropPath?.loadImage()?.let {
        LaunchedEffect(it) {
            dominantColorState.updateFrom(Url(it))
        }
    }

    Card(modifier = modifier.clickable { onItemClick(movieId) }) {
        Box {
            //region Movie Cover Image
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center),
                model = backdropPath?.loadImage(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
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
                            listOf(Color.Transparent, dominantColorState.color)
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
                    text = title ?: stringResource(Res.string.unknown_movie),
                    maxLines = 2,
                    style = MaterialTheme.typography.headlineLarge.copy(fontSize = 26.sp),
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start,
                    color = dominantColorState.onColor,
                )

                voteAverage?.let {
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieCardPagerIndicator(
    pagerState: PagerState,
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
        repeat(pagerState.pageCount) { currentPage ->
            Canvas(modifier = Modifier.size(indicatorSize), onDraw = {
                drawCircle(
                    color = if (pagerState.currentPage == currentPage) {
                        activeColor
                    } else {
                        inactiveColor
                    }
                )
            })
        }
    }
}

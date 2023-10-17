package com.vickikbt.shared.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.unit.dp
import com.seiko.imageloader.rememberImagePainter
import com.vickikbt.shared.domain.models.Movie
import com.vickikbt.shared.utils.commonImageLoader
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

    val boxWidth by animateDpAsState(targetValue = if (isHovered) 400.dp else 200.dp)

    Box(modifier.width(boxWidth)
        .clickable { onItemClick(movie) }
        .onPointerEvent(PointerEventType.Enter) { isHovered = true }
        .onPointerEvent(PointerEventType.Exit) { isHovered = false }) {
        //region Movie Cover Image
        commonImageLoader {
            /*val painter = rememberImagePainter(
                if (isHovered) movie.backdropPath?.loadImage()
                    ?: "" else movie.posterPath?.loadImage() ?: ""
            )*/

            val painter = rememberImagePainter(movie.posterPath?.loadImage() ?: "")

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
    }

}

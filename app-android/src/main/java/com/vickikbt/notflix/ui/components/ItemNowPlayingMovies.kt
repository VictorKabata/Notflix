package com.vickikbt.notflix.ui.components

import android.util.Log
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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.vickikbt.notflix.ui.theme.Black
import com.vickikbt.notflix.ui.theme.Golden
import com.vickikbt.notflix.util.PaletteGenerator
import com.vickikbt.notflix.util.loadImage
import com.vickikbt.shared.domain.models.Movie
import com.vickikbt.shared.utils.getRating

@ExperimentalCoilApi
@Composable
fun ItemNowPlayingMovies(
    modifier: Modifier = Modifier,
    movie: Movie,
    isLoading: Boolean = true,
    onItemClick: () -> Unit
) {
    val defaultDominantColor = MaterialTheme.colors.surface
    val defaultDominantTextColor = MaterialTheme.colors.onSurface
    var dominantColor by remember { mutableStateOf(defaultDominantColor) }
    var dominantTextColor by remember { mutableStateOf(defaultDominantTextColor) }

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

    Box(modifier = modifier.clickable { onItemClick() }) {
        //region Movie Cover Image
        Image(
            modifier = Modifier
                .placeholder(visible = isLoading, color = Color.Gray.copy(alpha = .8f))
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
                .height(210.dp)
                .align(Alignment.BottomCenter)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            if (isLoading) Color.Transparent else dominantColor
                        )
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
                modifier = Modifier.placeholder(
                    visible = isLoading,
                    color = Color.Gray,
                    shape = RoundedCornerShape(0.dp)
                ),
                text = movie.title ?: "Unknown movie",
                fontSize = 32.sp,
                maxLines = 2,
                style = MaterialTheme.typography.h6,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
                color = dominantTextColor
            )

            RatingBar(
                modifier = Modifier.placeholder(
                    visible = isLoading,
                    color = Color.Gray,
                    shape = RoundedCornerShape(0.dp)
                ),
                value = movie.voteAverage?.getRating()?.toFloat() ?: 0f,
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
        }
    }
}

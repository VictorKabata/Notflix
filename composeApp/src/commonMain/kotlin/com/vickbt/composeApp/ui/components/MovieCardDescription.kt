package com.vickbt.composeApp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.vickbt.composeApp.domain.models.MovieDetails
import com.vickbt.composeApp.utils.loadImage

@Composable
fun MovieCardDescription(
    modifier: Modifier = Modifier,
    movie: MovieDetails,
    maxLine: Int = 3,
    overFlowText: String = "See more",
    onItemClick: (MovieDetails) -> Unit
) {
    var dominantColor by remember { mutableStateOf(Color.DarkGray) }
    var dominantTextColor by remember { mutableStateOf(Color.LightGray) }

    Card(modifier = modifier.clickable { onItemClick(movie) }) {
        Box {
            //region Movie Cover Image
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center),
                model = movie.backdropPath?.loadImage(),
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
                            listOf(Color.Transparent, dominantColor)
                        )
                    )
            )
            //endregion

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .align(Alignment.BottomCenter)
            ) {
                Text(
                    modifier = Modifier,
                    text = movie.title ?: "Unknown movie",
                    fontSize = 24.sp,
                    maxLines = 1,
                    style = MaterialTheme.typography.titleMedium,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start,
                    color = dominantTextColor,
                    lineHeight = 30.sp
                )

                movie.overview?.let {
                    ExpandableText(
                        modifier = Modifier.padding(bottom = 4.dp),
                        text = it,
                        overFlowText = overFlowText,
                        minimizedMaxLines = maxLine
                    )
                }
            }
        }
    }
}

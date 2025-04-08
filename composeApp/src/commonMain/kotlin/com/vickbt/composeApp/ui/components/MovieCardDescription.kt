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
import androidx.compose.runtime.LaunchedEffect
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
import com.kmpalette.loader.NetworkLoader
import com.kmpalette.rememberDominantColorState
import com.vickbt.composeApp.domain.models.MovieDetails
import com.vickbt.composeApp.utils.loadImage
import com.vickbt.shared.resources.Res
import com.vickbt.shared.resources.see_more
import io.ktor.http.Url
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import network.chaintech.sdpcomposemultiplatform.sdp
import org.jetbrains.compose.resources.stringResource

@Composable
fun MovieCardDescription(
    modifier: Modifier = Modifier,
    movie: MovieDetails,
    networkLoader: NetworkLoader,
    maxLine: Int = 3,
    overFlowText: String = stringResource(Res.string.see_more),
    onItemClick: (MovieDetails) -> Unit
) {
    val dominantColorState = rememberDominantColorState(
        loader = networkLoader,
        defaultColor = Color.DarkGray,
        defaultOnColor = Color.LightGray,
        coroutineContext = Dispatchers.IO
    )

    movie.backdropPath?.loadImage()?.let {
        LaunchedEffect(it) {
            dominantColorState.updateFrom(Url(it))
        }
    }

    Card(
        modifier = modifier.height(200.sdp).clickable { onItemClick(movie) },
        shape = MaterialTheme.shapes.extraSmall
    ) {
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
                    .height(120.dp)
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
                    .padding(4.dp)
                    .align(Alignment.BottomCenter)
            ) {
                Text(
                    modifier = Modifier,
                    text = movie.title,
                    fontSize = 24.sp,
                    maxLines = 1,
                    style = MaterialTheme.typography.titleLarge,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start,
                    color = dominantColorState.onColor,
                    lineHeight = 30.sp
                )

                ExpandableText(
                    modifier = Modifier,
                    text = movie.overview,
                    overFlowText = overFlowText,
                    minimizedMaxLines = maxLine,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

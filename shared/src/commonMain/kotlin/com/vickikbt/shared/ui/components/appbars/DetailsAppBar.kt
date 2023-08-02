package com.vickikbt.shared.ui.components.appbars

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import com.vickikbt.shared.domain.models.MovieDetails
import com.vickikbt.shared.utils.getMovieDuration
import com.vickikbt.shared.utils.loadImage

val COLLAPSED_TOP_BAR_HEIGHT = 56.dp
val EXPANDED_TOP_BAR_HEIGHT = 350.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollapsedTopBar(
    modifier: Modifier = Modifier,
    isCollapsed: Boolean,
    movieDetails: MovieDetails?
) {
    val color: Color by animateColorAsState(
        if (isCollapsed) MaterialTheme.colorScheme.background else Color.Transparent
    )

    AnimatedVisibility(visible = isCollapsed) {
        /*TopAppBar(
            modifier = modifier
                .fillMaxWidth()
                .height(COLLAPSED_TOP_BAR_HEIGHT),
            title = {
                Text(
                    modifier = Modifier,
                    text = movieDetails?.title ?: "Unknown movie",
                    style = MaterialTheme.typography.h6,
                    fontSize = 20.sp,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    color = MaterialTheme.colors.onSurface
                )
            },
            navigationIcon = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colors.onSurface
                    )
                }
            },
            actions = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Rounded.Share,
                        contentDescription = "Share",
                        tint = MaterialTheme.colors.onSurface
                    )
                }

                IconButton(
                    onClick = {
                        movieDetails?.let { }
                        // isFavourite?.let { isFavourite = !it }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Favorite,
                        contentDescription = "Favorite"
                    )
                }
            },
            elevation = if (isCollapsed) 2.dp else 0.dp,
            backgroundColor = color
        )*/
        Text(
            modifier = modifier.background(color),
            text = movieDetails?.title ?: "Unknown movie",
            style = MaterialTheme.typography.titleMedium,
            fontSize = 20.sp,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            maxLines = 1,
            color = MaterialTheme.colorScheme.onSurface
        )
    }

}

@Composable
fun ExpandedTopBar(
    modifier: Modifier = Modifier,
    movieDetails: MovieDetails?
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(EXPANDED_TOP_BAR_HEIGHT)
    ) {

        val defaultDominantColor = MaterialTheme.colorScheme.surface
        val defaultDominantTextColor = MaterialTheme.colorScheme.onSurface
        var dominantColor by remember { mutableStateOf(defaultDominantColor) }
        var dominantTextColor by remember { mutableStateOf(defaultDominantTextColor) }

        val painter = rememberAsyncImagePainter(movieDetails?.backdropPath?.loadImage() ?: "")

        Image(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            painter = painter,
            contentDescription = movieDetails?.title,
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = movieDetails?.title ?: "Unknown movie",
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = dominantTextColor,
                fontSize = 32.sp
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                modifier = Modifier,
                text = movieDetails?.runtime?.getMovieDuration() ?: "",
                color = dominantTextColor,
                style = MaterialTheme.typography.labelMedium,
                fontSize = 14.sp
            )
        }
    }
}



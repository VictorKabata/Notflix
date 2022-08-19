package com.vickikbt.notflix.ui.components.app_bars

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import com.vickikbt.notflix.R
import com.vickikbt.notflix.ui.theme.Gray
import com.vickikbt.notflix.ui.theme.TextSecondary
import com.vickikbt.notflix.util.PaletteGenerator
import com.vickikbt.notflix.util.getMovieDuration
import com.vickikbt.notflix.util.loadImage
import com.vickikbt.shared.domain.models.MovieDetails
import io.github.aakira.napier.Napier
import me.onebone.toolbar.CollapsingToolbarScaffoldState

@ExperimentalCoilApi
@Composable
fun DetailsAppBar(
    modifier: Modifier = Modifier,
    collapsingScrollState: CollapsingToolbarScaffoldState,
    movieDetails: MovieDetails?,
    onNavigationIconClick: () -> Unit,
    onShareIconClick: () -> Unit,
    onFavoriteIconClick: () -> Unit
) {

    // Return progress on collapsing toolbar - 1.0f=Expanded, 0.0f=Collapsed
    val toolbarProgress = collapsingScrollState.toolbarState.progress

    val defaultDominantColor = MaterialTheme.colors.surface
    val defaultDominantTextColor = MaterialTheme.colors.onSurface
    var dominantColor by remember { mutableStateOf(defaultDominantColor) }
    var dominantTextColor by remember { mutableStateOf(defaultDominantTextColor) }

    val painter = rememberImagePainter(data = movieDetails?.backdropPath?.loadImage())

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

    val imageHeight by animateDpAsState(
        targetValue = 350.dp * toolbarProgress.coerceAtLeast(.16f),
        animationSpec = tween(durationMillis = 3000)
    )
    val backgroundColor by animateColorAsState(targetValue = MaterialTheme.colors.surface.copy(1 - toolbarProgress))
    val contentColor by animateColorAsState(targetValue = if (toolbarProgress == 1f) MaterialTheme.colors.surface else Color.Transparent)

    Napier.e("Toolbar progress: $toolbarProgress")
    Napier.e("Image height: $imageHeight")

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .graphicsLayer { alpha = toolbarProgress }
            .placeholder(
                visible = movieDetails == null,
                color = Gray,
                highlight = PlaceholderHighlight.shimmer(highlightColor = TextSecondary)
            )
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            painter = painter,
            contentDescription = stringResource(R.string.movie_poster),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(210.dp)
                .align(Alignment.BottomCenter)
                .background(
                    Brush.verticalGradient(listOf(Color.Transparent, dominantColor))
                )
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        )
        {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = movieDetails?.title ?: stringResource(R.string.unknown_movie),
                style = MaterialTheme.typography.h6,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = dominantTextColor,
                fontSize = 32.sp
            )

            Text(
                modifier = Modifier,
                text = movieDetails?.runtime?.getMovieDuration() ?: "",
                color = dominantTextColor,
                style = MaterialTheme.typography.h5,
                fontSize = 14.sp
            )
        }
    }

    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = {
            Text(
                modifier = Modifier.graphicsLayer { alpha = 1 - toolbarProgress },
                text = movieDetails?.title ?: stringResource(R.string.unknown_movie),
                style = MaterialTheme.typography.h6,
                fontSize = 20.sp,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                maxLines = 1,
                color = MaterialTheme.colors.onSurface
            )
        },
        navigationIcon = {
            IconButton(onClick = { onNavigationIconClick() }) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    tint = MaterialTheme.colors.onSurface
                )
            }
        },
        actions = {
            IconButton(onClick = { onShareIconClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_share),
                    contentDescription = stringResource(id = R.string.share),
                    tint = MaterialTheme.colors.onSurface
                )
            }

            IconButton(onClick = { onFavoriteIconClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_favourite),
                    contentDescription = stringResource(id = R.string.title_favorites),
                    tint = MaterialTheme.colors.onSurface
                )
            }
        },
        contentColor = contentColor,
        backgroundColor = backgroundColor,
        elevation = 0.dp
    )
}

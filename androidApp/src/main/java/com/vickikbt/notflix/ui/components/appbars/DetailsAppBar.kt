package com.vickikbt.notflix.ui.components.appbars

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
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
import com.vickikbt.notflix.util.loadImage
import com.vickikbt.shared.domain.models.MovieDetails
import com.vickikbt.shared.utils.getMovieDuration
import me.onebone.toolbar.CollapsingToolbarScaffoldState

@ExperimentalCoilApi
@Composable
fun DetailsAppBar(
    modifier: Modifier = Modifier,
    collapsingScrollState: CollapsingToolbarScaffoldState,
    movieDetails: MovieDetails?,
    onNavigationIconClick: () -> Unit,
    onShareIconClick: () -> Unit,
    onFavoriteIconClick: (MovieDetails) -> Unit
) {
    // Return progress on collapsing toolbar - 1.0f=Expanded, 0.0f=Collapsed
    val scrollProgress = collapsingScrollState.toolbarState.progress

    val defaultDominantColor = MaterialTheme.colors.surface
    val defaultDominantTextColor = MaterialTheme.colors.onSurface
    var dominantColor by remember { mutableStateOf(defaultDominantColor) }
    var dominantTextColor by remember { mutableStateOf(defaultDominantTextColor) }

    var isFavourite = mutableStateOf(movieDetails?.isFavourite)

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

    val backgroundColor by animateColorAsState(targetValue = MaterialTheme.colors.surface.copy(1 - scrollProgress))
    val contentColor by animateColorAsState(targetValue = if (scrollProgress == 1f) MaterialTheme.colors.surface else Color.Transparent)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(350.dp)
            .graphicsLayer { alpha = scrollProgress }
            .placeholder(
                visible = movieDetails == null,
                color = Gray,
                highlight = PlaceholderHighlight.shimmer(highlightColor = TextSecondary)
            )
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
                .aspectRatio(scrollProgress.coerceAtLeast(.1f)),
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
                .graphicsLayer { alpha = scrollProgress }
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
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
                modifier = Modifier.graphicsLayer { alpha = 1 - scrollProgress },
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

            IconButton(
                onClick = {
                    movieDetails?.let { onFavoriteIconClick(it) }
                    isFavourite.value?.let { isFavourite.value = !it }
                }
            ) {
                Icon(
                    painter = if (isFavourite.value == true) painterResource(id = R.drawable.ic_favourite_selected)
                    else painterResource(id = R.drawable.ic_favourite),
                    contentDescription = stringResource(id = R.string.title_favorites),
                    tint = if (isFavourite.value == true) colorResource(id = R.color.colorPrimary)
                    else MaterialTheme.colors.onSurface
                )
            }
        },
        contentColor = contentColor,
        backgroundColor = backgroundColor,
        elevation = 0.dp
    )
}

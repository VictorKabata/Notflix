package com.vickikbt.notflix.ui.components.app_bars

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import com.vickikbt.notflix.R
import com.vickikbt.notflix.ui.screens.details.MoviePoster
import com.vickikbt.notflix.ui.theme.Gray
import com.vickikbt.notflix.ui.theme.TextSecondary
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

    val imageHeight by animateDpAsState(
        targetValue = 350.dp * toolbarProgress.coerceAtLeast(.16f),
        animationSpec = tween(easing = FastOutLinearInEasing)
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
        MoviePoster(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer { alpha = toolbarProgress }
                .align(Alignment.Center),
            movieDetails = movieDetails
        )
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

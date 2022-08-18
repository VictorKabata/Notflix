package com.vickikbt.notflix.ui.components.app_bars

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
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

    val textSize = (18 + (30 - 12) * collapsingScrollState.toolbarState.progress).sp

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .placeholder(
                visible = movieDetails == null,
                color = Gray,
                highlight = PlaceholderHighlight.shimmer(highlightColor = TextSecondary)
            )
    ) {
        MoviePoster(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
                .alpha(if (textSize.value == 18f) 0f else 1f),
            movieDetails = movieDetails
        )
    }

    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = {
            Text(
                modifier = Modifier.alpha(if (textSize.value == 18f) 1f else 0f),
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
        contentColor = if (textSize.value == 18f) Color.Transparent else MaterialTheme.colors.onSurface,
        backgroundColor = if (textSize.value == 18f) MaterialTheme.colors.surface else Color.Transparent,
        elevation = 0.dp
    )
}

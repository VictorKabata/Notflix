package com.vickbt.composeApp.ui.components.appbars

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.kmpalette.loader.NetworkLoader
import com.kmpalette.rememberDominantColorState
import com.vickbt.composeApp.domain.models.MovieDetails
import com.vickbt.composeApp.ui.components.collapsingToolbar.CollapsingToolbarScaffoldState
import com.vickbt.composeApp.utils.DetailsUiState
import com.vickbt.composeApp.utils.getMovieDuration
import com.vickbt.composeApp.utils.loadImage
import com.vickbt.shared.resources.Res
import com.vickbt.shared.resources.unknown_movie
import io.ktor.http.Url
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import network.chaintech.sdpcomposemultiplatform.sdp
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsAppBar(
    modifier: Modifier = Modifier,
    collapsingScrollState: CollapsingToolbarScaffoldState,
    movieDetailsState: DetailsUiState,
    networkLoader: NetworkLoader,
    onNavigationIconClick: () -> Unit,
    onShareIconClick: () -> Unit,
    onFavoriteIconClick: (MovieDetails, Boolean?) -> Unit
) {
    // Return progress on collapsing toolbar - 1.0f=Expanded, 0.0f=Collapsed
    val scrollProgress = collapsingScrollState.toolbarState.progress

    val dominantColorState = rememberDominantColorState(
        loader = networkLoader,
        defaultColor = Color.DarkGray,
        defaultOnColor = Color.LightGray,
        coroutineContext = Dispatchers.IO
    )

    movieDetailsState.movieDetails?.backdropPath?.loadImage()?.let {
        LaunchedEffect(it) {
            dominantColorState.updateFrom(Url(it))
        }
    }

    val movieDetails = movieDetailsState.movieDetails
    var isFavourite = movieDetailsState.isFavorite

    val backgroundColor by animateColorAsState(
        targetValue = MaterialTheme.colorScheme.surface.copy(1 - scrollProgress)
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(280.sdp)
            .graphicsLayer { alpha = scrollProgress }
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
                .aspectRatio(scrollProgress.coerceAtLeast(.1f)),
            model = movieDetails?.backdropPath?.loadImage(),
            contentDescription = movieDetails?.title,
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.sdp)
                .align(Alignment.BottomCenter)
                .background(
                    Brush.verticalGradient(
                        listOf(Color.Transparent, dominantColorState.color)
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .graphicsLayer { alpha = scrollProgress }
                .padding(horizontal = 16.sdp, vertical = 6.sdp)
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(
                space = 1.sdp,
                alignment = Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = movieDetails?.title ?: stringResource(Res.string.unknown_movie),
                style = MaterialTheme.typography.headlineLarge,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = dominantColorState.onColor,
                textAlign = TextAlign.Start
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = movieDetails?.runtime?.getMovieDuration() ?: "",
                color = dominantColorState.onColor,
                style = MaterialTheme.typography.titleSmall
            )
        }
    }

    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = {
            Text(
                modifier = Modifier.basicMarquee().graphicsLayer { alpha = 1 - scrollProgress },
                text = movieDetails?.title ?: stringResource(Res.string.unknown_movie),
                style = MaterialTheme.typography.headlineMedium,
                fontSize = 20.sp,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                maxLines = 1,
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        navigationIcon = {
            IconButton(onClick = { onNavigationIconClick() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        },
        actions = {
            IconButton(onClick = { onShareIconClick() }) {
                Icon(
                    imageVector = Icons.Rounded.Share,
                    contentDescription = "Share",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            IconButton(
                onClick = {
                    isFavourite?.let { isFavourite = !it }
                    movieDetails?.let { onFavoriteIconClick(it, isFavourite) }
                }
            ) {
                Icon(
                    imageVector = if (isFavourite == true) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                    contentDescription = "Favourite",
                    tint = if (isFavourite == true) Color.Red else MaterialTheme.colorScheme.onSurface
                )
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = backgroundColor)
    )
}

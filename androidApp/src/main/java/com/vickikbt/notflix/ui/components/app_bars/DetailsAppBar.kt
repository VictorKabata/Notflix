package com.vickikbt.notflix.ui.components.app_bars

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vickikbt.notflix.R

@Composable
fun DetailsAppBar(
    modifier: Modifier = Modifier,
    scrollOffset: Float,
    title: String?,
    onNavigationIconClick: () -> Unit,
    onShareIconClick: () -> Unit,
    onFavoriteIconClick: () -> Unit
) {

    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                modifier = Modifier,
                text = title ?: stringResource(R.string.unknown_movie),
                style = MaterialTheme.typography.h6,
                fontSize = 20.sp,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                maxLines = 1,
                color = MaterialTheme.colors.onSurface.copy(alpha = 1f - scrollOffset)
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
        contentColor = Color.White,
        backgroundColor = Color.Black.copy(alpha = 1f - scrollOffset),
        elevation = 0.dp
    )
}

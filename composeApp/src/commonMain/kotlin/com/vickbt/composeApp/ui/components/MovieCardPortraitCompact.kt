package com.vickbt.composeApp.ui.components

import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import coil3.compose.AsyncImage
import com.vickbt.composeApp.utils.loadImage
import com.vickbt.shared.resources.Res
import com.vickbt.shared.resources.unknown_movie
import network.chaintech.sdpcomposemultiplatform.sdp
import org.jetbrains.compose.resources.stringResource

@Composable
fun MovieCardPortraitCompact(
    modifier: Modifier = Modifier,
    movieId: Int,
    posterPath: String? = null,
    title: String? = null,
    onItemClick: (Int) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.sdp)
    ) {
        Card(
            modifier = Modifier
                .width(88.sdp)
                .fillMaxHeight()
                .clickable { onItemClick(movieId) },
            elevation = CardDefaults.cardElevation(8.sdp),
            shape = MaterialTheme.shapes.extraSmall
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = posterPath?.loadImage(),
                contentDescription = title,
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
            )
        }

        Text(
            modifier = Modifier.sizeIn(maxWidth = 88.sdp).basicMarquee(),
            text = title ?: stringResource(Res.string.unknown_movie),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start
        )
    }
}

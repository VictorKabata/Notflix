package com.vickbt.composeApp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import coil3.compose.AsyncImage
import com.vickbt.composeApp.domain.models.Actor
import com.vickbt.composeApp.ui.theme.TextSecondary
import com.vickbt.composeApp.utils.loadImage
import network.chaintech.sdpcomposemultiplatform.sdp

@Composable
fun ItemMovieCast(modifier: Modifier = Modifier, actor: Actor) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(space = 1.sdp)
    ) {
        AsyncImage(
            modifier = Modifier.size(64.sdp).clip(CircleShape),
            model = actor.profilePath?.loadImage(),
            contentDescription = "Cast",
            contentScale = ContentScale.Crop,
        )

        Text(
            modifier = Modifier.widthIn(max = 64.sdp),
            text = actor.name ?: "Unknown actor",
            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            maxLines = 1,
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            modifier = Modifier.widthIn(max = 64.sdp),
            text = actor.character ?: "Unknown character",
            style = MaterialTheme.typography.labelSmall,
            color = TextSecondary,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            maxLines = 1
        )
    }
}

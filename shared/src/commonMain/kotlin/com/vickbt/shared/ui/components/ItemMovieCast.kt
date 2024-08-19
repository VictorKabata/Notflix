package com.vickbt.shared.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.vickbt.shared.domain.models.Actor
import com.vickbt.shared.ui.theme.TextSecondary
import com.vickbt.shared.utils.loadImage

@Composable
fun ItemMovieCast(
    modifier: Modifier = Modifier,
    actor: Actor,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(space = 1.dp),
    ) {
        AsyncImage(
            modifier = Modifier.size(80.dp).clip(CircleShape),
            model = actor.profilePath?.loadImage(),
            contentDescription = "Cast",
            contentScale = ContentScale.Crop,
        )

        Text(
            modifier = Modifier.width(78.dp),
            text = actor.name ?: "Unknown actor",
            style = MaterialTheme.typography.titleMedium,
            fontSize = 14.sp,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            maxLines = 1,
            color = MaterialTheme.colorScheme.onSurface,
        )

        Text(
            modifier = Modifier.width(77.dp),
            text = actor.character ?: "Unknown character",
            style = MaterialTheme.typography.labelSmall,
            fontSize = 12.sp,
            color = TextSecondary,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            maxLines = 1,
        )
    }
}

package com.vickikbt.shared.ui.components

import androidx.compose.foundation.Image
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
import com.seiko.imageloader.rememberImagePainter
import com.vickikbt.shared.domain.models.Actor
import com.vickikbt.shared.ui.theme.TextSecondary
import com.vickikbt.shared.utils.commonImageLoader
import com.vickikbt.shared.utils.loadImage

@Composable
fun ItemMovieCast(modifier: Modifier = Modifier, actor: Actor) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(space = 1.dp)
    ) {
        commonImageLoader {
            val painter = rememberImagePainter(actor.profilePath?.loadImage() ?: "")

            Image(
                modifier = Modifier.size(80.dp).clip(CircleShape),
                painter = painter,
                contentDescription = "Cast",
                contentScale = ContentScale.Crop
            )
        }

        Text(
            modifier = Modifier.width(78.dp),
            text = actor.name ?: "Unknown actor",
            style = MaterialTheme.typography.titleMedium,
            fontSize = 14.sp,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            maxLines = 1,
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            modifier = Modifier.width(77.dp),
            text = actor.character ?: "Unknown character",
            style = MaterialTheme.typography.labelSmall,
            fontSize = 12.sp,
            color = TextSecondary,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            maxLines = 1
        )
    }
}

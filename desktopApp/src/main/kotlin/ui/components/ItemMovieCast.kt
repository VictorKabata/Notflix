package ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vickikbt.shared.domain.models.Actor
import ui.theme.TextSecondary
import utils.AsyncImage
import utils.loadImage
import utils.loadImageBitmap

@Composable
fun ItemMovieCast(modifier: Modifier = Modifier, actor: Actor) {
    val imageUrl = actor.profilePath?.loadImage()

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            load = { loadImageBitmap(imageUrl) },
            painterFor = { remember { BitmapPainter(it) } },
            contentDescription = "Cast",
            contentScale = ContentScale.Crop
        )

        Text(
            modifier = Modifier.width(78.dp),
            text = actor.name ?: "Unknown actor",
            style = MaterialTheme.typography.h6,
            fontSize = 14.sp,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            maxLines = 1,
            color = MaterialTheme.colors.onSurface
        )

        Text(
            modifier = Modifier.width(77.dp),
            text = actor.character ?: "Unknown character",
            style = MaterialTheme.typography.h4,
            fontSize = 12.sp,
            color = TextSecondary,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            maxLines = 1
        )
    }
}

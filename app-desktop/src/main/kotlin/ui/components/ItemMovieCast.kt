package ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vickikbt.shared.domain.models.Actor
import io.kamel.image.KamelImage
import io.kamel.image.lazyPainterResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import ui.theme.TextSecondary
import utils.loadImage

@Composable
fun ItemMovieCast(modifier: Modifier = Modifier, actor: Actor) {
    val imageUrl = actor.profilePath?.loadImage()

    val painterResource = lazyPainterResource(imageUrl ?: "") {
        coroutineContext = Job() + Dispatchers.IO
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Card(
            modifier = Modifier.size(90.dp),
            shape = CircleShape
        ) {
            KamelImage(
                modifier = Modifier.fillMaxSize(),
                resource = painterResource,
                contentDescription = "Cast",
                contentScale = ContentScale.Crop
            )
        }

        Text(
            modifier = Modifier.widthIn(max = 90.dp),
            text = actor.name ?: "Unknown actor",
            style = MaterialTheme.typography.h6,
            fontSize = 14.sp,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            maxLines = 1,
            color = MaterialTheme.colors.onSurface
        )

        Text(
            modifier = Modifier.widthIn(max = 90.dp),
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

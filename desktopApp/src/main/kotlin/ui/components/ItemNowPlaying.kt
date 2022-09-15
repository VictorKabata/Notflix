package ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vickikbt.shared.domain.models.Movie
import utils.AsyncImage
import utils.loadImage
import utils.loadImageBitmap

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ItemNowPlayingMovies(
    modifier: Modifier = Modifier,
    movie: Movie,
    onItemClick: (Movie) -> Unit
) {
    var isHovered by remember { mutableStateOf(false) }

    val boxWidth by animateDpAsState(targetValue = if (isHovered) 400.dp else 200.dp)

    val imageUrl = (if (isHovered) movie.backdropPath else movie.posterPath)?.loadImage()

    Box(
        modifier = modifier.width(boxWidth)
            .clickable { onItemClick(movie) }
            .onPointerEvent(PointerEventType.Enter) { isHovered = true }
            .onPointerEvent(PointerEventType.Exit) { isHovered = false },
    ) {

        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            load = { loadImageBitmap(imageUrl) },
            painterFor = { remember { BitmapPainter(it) } },
            contentDescription = "Movie Backdrop Poster",
            contentScale = ContentScale.Crop
        )

        //region Movie Title
        if (isHovered) {
            Text(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp)
                    .align(Alignment.BottomStart),
                text = movie.title ?: "Unknown Movie",
                fontSize = 48.sp,
                maxLines = 2,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colors.onSurface
            )
        }
        //endregion
    }
}

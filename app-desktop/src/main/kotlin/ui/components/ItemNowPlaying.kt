package ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.vickikbt.shared.domain.models.Movie
import io.kamel.image.KamelImage
import io.kamel.image.lazyPainterResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import utils.loadImage

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ItemNowPlayingMovies(
    modifier: Modifier = Modifier,
    movie: Movie,
    onItemClick: (Movie) -> Unit
) {
    var isHovered by remember { mutableStateOf(false) }

    val boxWidth by animateDpAsState(targetValue = if (isHovered) 400.dp else 200.dp)

    val imageUrl = movie.posterPath?.loadImage()

    val painterResource = lazyPainterResource(imageUrl ?: "") {
        coroutineContext = Job() + Dispatchers.IO
    }

    Box(
        modifier = modifier.width(boxWidth)
            .clickable { onItemClick(movie) }
            .onPointerEvent(PointerEventType.Enter) { isHovered = true }
            .onPointerEvent(PointerEventType.Exit) { isHovered = false },
    ) {
        KamelImage(
            modifier = Modifier.fillMaxSize(),
            resource = painterResource,
            contentDescription = "Movie Backdrop Poster",
            contentScale = ContentScale.Crop,
            animationSpec = tween()
        )

        //region Movie Title
        /*if (isHovered) {
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
        }*/
        //endregion
    }
}

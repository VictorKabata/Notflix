package ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vickikbt.shared.domain.models.Movie
import utils.AsyncImage
import utils.loadImageBitmap

@Composable
fun ItemNowPlayingMovies(
    modifier: Modifier = Modifier,
    movie: Movie,
    onItemClick: (Movie) -> Unit
) {
    val imageUrl = "https://image.tmdb.org/t/p/original/${movie.backdropPath}"

    Box(modifier = modifier) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            load = { loadImageBitmap(imageUrl) },
            painterFor = { remember { BitmapPainter(it) } },
            contentDescription = "Movie Backdrop Poster",
            contentScale = ContentScale.FillWidth
        )

        //region Movie Title
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
        //endregion
    }
}

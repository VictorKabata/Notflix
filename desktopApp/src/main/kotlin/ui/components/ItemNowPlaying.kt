package ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import utils.loadImage
import utils.loadImageBitmap

@Composable
fun ItemNowPlayingMovies(
    modifier: Modifier = Modifier,
    movie: Movie,
    onItemClick: (Movie) -> Unit
) {
    val imageUrl = movie.backdropPath?.loadImage()

    Box(modifier = modifier.fillMaxWidth().clickable { onItemClick(movie) }) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            load = { loadImageBitmap(imageUrl) },
            painterFor = { remember { BitmapPainter(it) } },
            contentDescription = "Movie Backdrop Poster",
            contentScale = ContentScale.Crop
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

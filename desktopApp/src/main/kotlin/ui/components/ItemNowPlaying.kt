package ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vickikbt.shared.domain.models.Movie
import utils.koil

@Composable
fun ItemNowPlayingMovies(
    modifier: Modifier = Modifier,
    movie: Movie,
    onItemClick: (Movie) -> Unit
) {
    val painter = koil(url = "https://image.tmdb.org/t/p/original/${movie.backdropPath}")

    Box(modifier = modifier) {
        painter?.let {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
                bitmap = it,
                contentDescription = null
            )
        }

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

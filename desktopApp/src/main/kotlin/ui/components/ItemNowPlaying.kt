package ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ui.screens.home.FakeMovie
import utils.koil

@Composable
fun ItemNowPlayingMovies(modifier: Modifier = Modifier, movie: FakeMovie, onItemClick: (FakeMovie) -> Unit) {
    val painter = koil(url = movie.posterPath)

    Box(modifier = modifier) {
        painter?.let {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.65F)
                    .align(Alignment.Center),
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
                bitmap = it,
                contentDescription = null
            )
        }

        //region Movie Title
        Text(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp).align(Alignment.BottomStart),
            text = movie.title,
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

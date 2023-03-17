package ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vickikbt.shared.domain.models.Movie
import utils.AsyncImage
import utils.loadImage
import utils.loadImageBitmap

@Composable
fun ItemSimilarMovies(movie: Movie) {
    val imageUrl = movie.posterPath?.loadImage()

    Column {
        Card(
            modifier = Modifier
                .width(150.dp)
                .height(220.dp),
            shape = RoundedCornerShape(4.dp)
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                load = { loadImageBitmap(imageUrl) },
                painterFor = { remember { BitmapPainter(it) } },
                contentDescription = "Movie poster",
                contentScale = ContentScale.Crop
            )
        }

        Text(
            modifier = Modifier.width(148.dp),
            text = movie.title ?: "Unknown movie",
            style = MaterialTheme.typography.h5,
            fontSize = 14.sp,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start,
            maxLines = 1,
            color = MaterialTheme.colors.onSurface,
        )
    }
}

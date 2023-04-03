package ui.components

import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vickikbt.shared.domain.models.Movie
import io.kamel.image.KamelImage
import io.kamel.image.lazyPainterResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import utils.loadImage

@Composable
fun ItemTrendingMovies(
    modifier: Modifier = Modifier,
    movie: Movie,
    onItemClick: (Movie) -> Unit
) {
    val imageUrl = movie.posterPath?.loadImage()

    val painterResource = lazyPainterResource(imageUrl ?: "") {
        coroutineContext = Job() + Dispatchers.IO
    }

    Column {
        Card(
            modifier = modifier
                .width(224.dp)
                .height(288.dp)
                .clickable { onItemClick(movie) },
            elevation = 8.dp,
            shape = RoundedCornerShape(4.dp)
        ) {
            KamelImage(
                modifier = Modifier.fillMaxSize(),
                resource = painterResource,
                contentDescription = "Movie poster",
                contentScale = ContentScale.Crop,
                animationSpec = tween()
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            modifier = Modifier.widthIn(min = 0.dp, max = 224.dp),
            text = movie.title ?: "Unknown movie",
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colors.onSurface,
            fontSize = 20.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start
        )
    }
}

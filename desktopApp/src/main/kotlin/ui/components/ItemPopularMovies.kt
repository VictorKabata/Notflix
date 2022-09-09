package ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
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
fun ItemPopularMovies(
    modifier: Modifier = Modifier,
    movie: Movie,
    onClickItem: (Movie) -> Unit
) {
    val defaultDominantColor = MaterialTheme.colors.surface
    val defaultDominantTextColor = MaterialTheme.colors.onSurface
    val dominantColor = remember { mutableStateOf(defaultDominantColor) }
    val dominantTextColor = remember { mutableStateOf(defaultDominantTextColor) }
    val dominantSubTextColor = remember { mutableStateOf(defaultDominantTextColor) }

    val imageUrl = movie.backdropPath?.loadImage()

    // var cardAspectRatio by remember { mutableStateOf(1F) }

    Card(
        modifier = modifier
            .width(480.dp)
            .height(320.dp)
            .clickable { onClickItem(movie) }
        /*.onPointerEvent(PointerEventType.Enter) {
            cardAspectRatio = 1.5f
        }.onPointerEvent(PointerEventType.Exit) {
            cardAspectRatio = 1f
        }*/,
        elevation = 8.dp,
        shape = RoundedCornerShape(4.dp)
    ) {

        Box(modifier = Modifier.fillMaxSize()) {

            //region Movie Cover
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                load = { loadImageBitmap(imageUrl) },
                painterFor = { remember { BitmapPainter(it) } },
                contentDescription = "Movie backdrop poster",
                contentScale = ContentScale.Crop
            )
            //endregion

            Column(
                modifier = Modifier
                    .padding(horizontal = 28.dp, vertical = 16.dp)
                    .align(Alignment.BottomStart),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                //region Movie Title
                Text(
                    text = movie.title ?: "Unknown movie",
                    fontSize = 26.sp,
                    maxLines = 2,
                    fontWeight = FontWeight.Black,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start,
                    color = dominantTextColor.value
                )
                //endregion

                //region Movie Release Date
                movie.releaseDate?.let {
                    Text(
                        modifier = Modifier,
                        text = it,
                        fontSize = 16.sp,
                        maxLines = 1,
                        style = MaterialTheme.typography.h5,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Start,
                        color = dominantSubTextColor.value
                    )
                }
                //endregion
            }
        }
    }
}

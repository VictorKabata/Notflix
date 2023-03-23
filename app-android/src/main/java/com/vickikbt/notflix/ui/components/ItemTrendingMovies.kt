package com.vickikbt.notflix.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.google.accompanist.placeholder.material.placeholder
import com.vickikbt.notflix.R
import com.vickikbt.notflix.util.loadImage
import com.vickikbt.shared.domain.models.Movie

@Composable
fun ItemTrendingMovies(
    movie: Movie,
    isLoading: Boolean = true,
    onItemClick: (Movie) -> Unit
) {
    // Log.e("VicKbt", "Is loading trending movies: $isLoading")

    Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
        Card(
            modifier = Modifier
                .width(150.dp)
                .fillMaxHeight()
                .clickable { onItemClick(movie) },
            elevation = 8.dp,
            shape = RoundedCornerShape(4.dp)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .placeholder(
                        visible = isLoading,
                        color = Color.Gray.copy(alpha = .8f),
                        shape = RoundedCornerShape(4.dp)
                    ),
                painter = rememberImagePainter(
                    data = movie.posterPath?.loadImage(),
                    builder = { crossfade(true) }
                ),
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
                contentDescription = stringResource(id = R.string.trending_movies)
            )
        }

        Text(
            modifier = Modifier
                .width(145.dp)
                .placeholder(
                    visible = isLoading,
                    color = Color.Gray,
                    shape = RoundedCornerShape(0.dp)
                ),
            text = movie.title ?: "Unknown movie",
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.onSurface,
            fontSize = 14.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start
        )
    }
}

@Preview
@Composable
private fun Preview() {
    ItemTrendingMovies(movie = Movie(title = "Cocaine Bear"), isLoading = false, onItemClick = {})
}

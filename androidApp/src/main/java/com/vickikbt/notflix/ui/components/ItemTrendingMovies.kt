package com.vickikbt.notflix.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.shimmer
import com.vickikbt.notflix.R
import com.vickikbt.notflix.util.loadImage
import com.vickikbt.shared.domain.models.Movie

@Composable
fun ItemTrendingMovies(
    movie: Movie,
    onItemClick: (Movie) -> Unit
) {

    Column(
        modifier = Modifier
            .placeholder(
                visible = false,
                color = Color.Gray,
                highlight = PlaceholderHighlight.shimmer(highlightColor = Color.White)
            ),
        verticalArrangement = Arrangement.spacedBy(3.dp)
    ) {

        Card(
            modifier = Modifier
                .width(150.dp)
                .fillMaxHeight()
                .clickable { onItemClick(movie) }
                .placeholder(
                    visible = false,
                    color = Color.Black,
                    highlight = PlaceholderHighlight.shimmer(highlightColor = Color.White)
                ),
            elevation = 8.dp,
            shape = RoundedCornerShape(4.dp)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .placeholder(
                        visible = false,
                        color = Color.Gray,
                        highlight = PlaceholderHighlight.shimmer(highlightColor = Color.White)
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
                    visible = false,
                    color = Color.Black,
                    highlight = PlaceholderHighlight.shimmer(highlightColor = Color.White)
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

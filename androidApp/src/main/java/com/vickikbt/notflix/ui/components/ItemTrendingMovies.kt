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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.vickikbt.notflix.R
import com.vickikbt.notflix.util.loadImage
import com.vickikbt.shared.domain.models.Movie

@Composable
fun ItemTrendingMovies(
    modifier: Modifier = Modifier,
    movie: Movie,
    onItemClick: (Movie) -> Unit
) {

    Column {

        Card(
            modifier = modifier
                .width(140.dp)
                .height(180.dp)
                .clickable { onItemClick(movie) },
            elevation = 8.dp,
            shape = RoundedCornerShape(4.dp)
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = rememberImagePainter(
                    data = movie.backdropPath?.loadImage(),
                    builder = { crossfade(true) }
                ),
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
                contentDescription = stringResource(id = R.string.trending_movies)
            )
        }

        Spacer(modifier = Modifier.height(3.dp))

        Text(
            modifier = Modifier.width(144.dp),
            text = movie.title ?: "Unknown movie",
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.onSurface,
            fontSize = 14.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start
        )
    }
}

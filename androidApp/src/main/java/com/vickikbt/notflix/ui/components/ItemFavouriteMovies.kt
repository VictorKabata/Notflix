package com.vickikbt.notflix.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.vickikbt.shared.domain.models.Movie
import com.vickikbt.notflix.util.loadImage

@Composable
fun ItemFavoriteMovie(movie: Movie) {
    Column(Modifier.fillMaxWidth(0.4f).height(100.dp), horizontalAlignment = Alignment.Start) {
        val painter = rememberImagePainter(data = movie.backdropPath?.loadImage(LocalContext.current)) {
            crossfade(true)
        }
        Image(
            painter = painter,
            contentDescription = "favorite movie image",
            modifier = Modifier
                .fillMaxWidth().height(70.dp),
            contentScale = ContentScale.Crop
        )
        Text(text = movie.title ?: " ", style = MaterialTheme.typography.h6, maxLines = 2)
    }
}

package com.vickikbt.shared.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seiko.imageloader.rememberImagePainter
import com.vickikbt.shared.domain.models.Movie
import com.vickikbt.shared.utils.commonImageLoader
import com.vickikbt.shared.utils.loadImage

@Composable
fun MovieCardPortraitCompact(
    modifier: Modifier = Modifier,
    movie: Movie,
    onItemClick: (Movie) -> Unit
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        Card(
            modifier = Modifier
                .width(150.dp)
                .fillMaxHeight()
                .clickable { onItemClick(movie) },
            elevation = CardDefaults.cardElevation(8.dp),
            shape = RoundedCornerShape(4.dp)
        ) {
            commonImageLoader {
                val painter = rememberImagePainter(movie.posterPath?.loadImage() ?: "")

                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .sizeIn(minHeight = 30.dp),
                    painter = painter,
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Crop,
                    contentDescription = "Trending movie poster"
                )
            }
        }

        Text(
            modifier = Modifier.width(145.dp),
            text = movie.title ?: "Unknown movie",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 14.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start
        )
    }
}

@Composable
private fun Preview() {
    MovieCardPortraitCompact(movie = Movie(title = "Cocaine Bear"), onItemClick = {})
}

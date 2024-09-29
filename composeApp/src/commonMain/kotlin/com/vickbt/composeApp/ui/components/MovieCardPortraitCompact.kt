package com.vickbt.composeApp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
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
import coil3.compose.AsyncImage
import com.vickbt.composeApp.domain.models.Movie
import com.vickbt.composeApp.utils.loadImage
import com.vickbt.shared.resources.Res
import com.vickbt.shared.resources.ic_theatre
import com.vickbt.shared.resources.unknown_movie
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun MovieCardPortraitCompact(
    modifier: Modifier = Modifier,
    movie: Movie,
    onItemClick: (Movie) -> Unit
) {
    Column(
        modifier = modifier.width(150.dp),
        verticalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .clickable { onItemClick(movie) },
            elevation = CardDefaults.cardElevation(8.dp),
            shape = RoundedCornerShape(4.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                model = movie.posterPath?.loadImage(),
                contentDescription = "Trending movie poster",
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                error = painterResource(Res.drawable.ic_theatre)
            )
        }

        Text(
            modifier=Modifier.wrapContentWidth(),
            text = movie.title ?: stringResource(Res.string.unknown_movie),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 14.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start
        )
    }
}

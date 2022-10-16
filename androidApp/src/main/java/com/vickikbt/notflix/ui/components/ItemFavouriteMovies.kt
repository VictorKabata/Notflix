package com.vickikbt.notflix.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle
import com.gowtham.ratingbar.StepSize
import com.vickikbt.notflix.R
import com.vickikbt.notflix.ui.theme.Golden
import com.vickikbt.notflix.ui.theme.Gray
import com.vickikbt.notflix.util.loadImage
import com.vickikbt.shared.domain.models.MovieDetails
import com.vickikbt.shared.utils.getRating

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ItemFavoriteMovie(movie: MovieDetails, onItemClick: (MovieDetails) -> Unit) {
    val painter = rememberImagePainter(data = movie.posterPath?.loadImage()) {
        crossfade(true)
    }

    Column {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp),
            shape = RoundedCornerShape(4.dp),
            onClick = { onItemClick(movie) }
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painter,
                contentDescription = stringResource(id = R.string.movie_poster),
                contentScale = ContentScale.Crop
            )
        }

        Text(
            modifier = Modifier.width(148.dp),
            text = movie.title ?: stringResource(id = R.string.unknown_movie),
            style = MaterialTheme.typography.h5,
            fontSize = 14.sp,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start,
            maxLines = 1,
            color = MaterialTheme.colors.onSurface,
        )

        // rating bar
        RatingBar(
            modifier = Modifier.fillMaxWidth(0.8f),
            value = movie.voteAverage?.getRating()?.toFloat() ?: 0f,
            numStars = 5,
            size = 15.dp,
            stepSize = StepSize.HALF,
            isIndicator = true,
            ratingBarStyle = RatingBarStyle.Normal,
            activeColor = Golden,
            inactiveColor = Gray,
            onValueChange = {},
            onRatingChanged = {}
        )
    }
}

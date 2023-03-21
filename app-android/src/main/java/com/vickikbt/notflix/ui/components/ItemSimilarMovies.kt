package com.vickikbt.notflix.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.google.accompanist.placeholder.placeholder
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle
import com.gowtham.ratingbar.StepSize
import com.vickikbt.notflix.ui.theme.Golden
import com.vickikbt.notflix.ui.theme.Gray
import com.vickikbt.notflix.util.loadImage
import com.vickikbt.shared.domain.models.Movie
import com.vickikbt.shared.utils.getRating

@Composable
fun ItemSimilarMovies(movie: Movie, isLoading: Boolean) {
    val painter = rememberImagePainter(data = movie.backdropPath?.loadImage())

    Column(verticalArrangement = Arrangement.spacedBy(1.dp)) {
        Card(
            modifier = Modifier
                .width(150.dp)
                .height(220.dp),
            shape = RoundedCornerShape(4.dp)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .placeholder(visible = isLoading, color = Color.Gray.copy(.8f)),
                painter = painter,
                contentDescription = stringResource(id = com.vickikbt.notflix.R.string.movie_poster),
                contentScale = ContentScale.Crop
            )
        }

        Text(
            modifier = Modifier
                .width(148.dp)
                .placeholder(visible = isLoading, color = Color.Gray),
            text = movie.title ?: stringResource(id = com.vickikbt.notflix.R.string.unknown_movie),
            style = MaterialTheme.typography.h5,
            fontSize = 14.sp,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start,
            maxLines = 1,
            color = MaterialTheme.colors.onSurface,
        )

        // rating bar
        RatingBar(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .placeholder(visible = isLoading, color = Color.Gray),
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

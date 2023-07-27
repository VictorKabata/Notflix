package com.vickikbt.shared.presentation.ui.components

import androidx.compose.runtime.Composable
import com.vickikbt.shared.domain.models.Movie

@Composable
fun ItemSimilarMovies(movie: Movie) {
    /*val painter = rememberImagePainter(data = movie.backdropPath?.loadImage())

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
    }*/
}

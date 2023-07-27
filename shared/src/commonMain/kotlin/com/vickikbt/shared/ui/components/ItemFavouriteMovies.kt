package com.vickikbt.shared.presentation.ui.components

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import com.vickikbt.shared.domain.models.MovieDetails

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ItemFavoriteMovie(movie: MovieDetails, onItemClick: (MovieDetails) -> Unit) {
    /*val painter = rememberImagePainter(data = movie.posterPath?.loadImage()) {
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
    }*/
}

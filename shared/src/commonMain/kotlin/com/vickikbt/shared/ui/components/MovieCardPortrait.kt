package com.vickikbt.shared.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vickikbt.shared.domain.models.Movie
import com.vickikbt.shared.ui.components.ratingbar.RatingBar
import com.vickikbt.shared.ui.components.ratingbar.RatingBarStyle
import com.vickikbt.shared.ui.components.ratingbar.StepSize
import com.vickikbt.shared.utils.getRating

@Composable
fun MovieCardPortrait(modifier: Modifier = Modifier, movie: Movie, onItemClick: (Movie) -> Unit) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(1.dp)) {
        MovieCardPortraitCompact(
            movie = movie,
            onItemClick = { onItemClick(movie) }
        )

        // rating bar
        RatingBar(
            modifier = Modifier.fillMaxWidth(0.8f),
            value = movie.voteAverage?.getRating()?.toFloat() ?: 0f,
            numOfStars = 5,
            size = 15.dp,
            stepSize = StepSize.HALF,
            isIndicator = true,
            style = RatingBarStyle.Fill()
        )
    }
}

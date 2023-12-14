package com.vickbt.shared.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vickbt.shared.domain.models.Movie
import com.vickbt.shared.ui.components.ratingbar.RatingBar
import com.vickbt.shared.ui.components.ratingbar.RatingBarStyle
import com.vickbt.shared.ui.components.ratingbar.StepSize
import com.vickbt.shared.utils.getRating

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

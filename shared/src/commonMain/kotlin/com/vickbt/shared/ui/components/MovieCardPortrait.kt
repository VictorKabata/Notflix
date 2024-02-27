package com.vickbt.shared.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
        Box(modifier = Modifier.fillMaxSize()) {
            MovieCardPortraitCompact(
                movie = movie,
                onItemClick = { onItemClick(movie) }
            )

            OutlinedText(
                modifier = Modifier.align(Alignment.TopEnd).padding(4.dp),
                text = movie.category.replace("_", " ")
            )
        }

        // rating bar
        RatingBar(
            modifier = Modifier.fillMaxWidth(0.8f),
            value = movie.rating?.getRating() ?: 0f,
            numOfStars = 5,
            size = 15.dp,
            stepSize = StepSize.HALF,
            isIndicator = true,
            style = RatingBarStyle.Fill()
        )
    }
}

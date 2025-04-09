package com.vickbt.composeApp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.vickbt.composeApp.domain.models.Movie
import com.vickbt.composeApp.ui.components.ratingbar.RatingBar
import com.vickbt.composeApp.ui.components.ratingbar.RatingBarStyle
import com.vickbt.composeApp.ui.components.ratingbar.StepSize
import com.vickbt.composeApp.utils.getRating
import network.chaintech.sdpcomposemultiplatform.sdp

@Composable
fun MovieCardPortrait(modifier: Modifier = Modifier, movie: Movie, onItemClick: (Movie) -> Unit) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(2.sdp)) {
        MovieCardPortraitCompact(
            movieId = movie.id,
            posterPath = movie.posterPath,
            title = movie.title,
            onItemClick = { onItemClick(movie) }
        )

        // rating bar
        RatingBar(
            modifier = Modifier.fillMaxWidth(0.8f),
            value = movie.voteAverage?.getRating()?.toFloat() ?: 0f,
            numOfStars = 5,
            size = 12.sdp,
            stepSize = StepSize.HALF,
            isIndicator = true,
            style = RatingBarStyle.Fill()
        )
    }
}

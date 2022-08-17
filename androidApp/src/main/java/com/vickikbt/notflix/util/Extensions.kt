package com.vickikbt.notflix.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.vickikbt.shared.presentation.presenters.SharedSettingsPresenter
import org.koin.androidx.compose.inject

/**
 * Append the image url with string to determine the image quality to be loaded
 */
@Composable
fun String.loadImage(): String {
    val settingsRepository: SharedSettingsPresenter by inject()

    val quality = when (settingsRepository.selectedImageQuality.collectAsState().value) {
        0 -> "original"
        else -> "w500"
    }

    return "https://image.tmdb.org/t/p/$quality/$this"
}

fun Double.getRating() = ((this.toFloat() * 5) / 10)

fun Double.getPopularity() = ((this.toInt() * 100) / 10).toString()

fun Int.getMovieDuration(): String {
    val startTime = "00:00"
    val hours = this / 60 + startTime.substring(0, 1).toInt()
    val mins = this % 60 + startTime.substring(3, 4).toInt()
    return "${hours}hrs ${mins}mins"
}

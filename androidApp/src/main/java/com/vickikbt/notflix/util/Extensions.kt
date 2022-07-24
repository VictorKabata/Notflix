package com.vickikbt.notflix.util

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.Velocity
import com.vickikbt.shared.presentation.presenters.SharedSettingsPresenter
import io.github.aakira.napier.Napier
import org.koin.androidx.compose.inject
import java.text.SimpleDateFormat

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

    Napier.e("Image quality: $quality")

    return "https://image.tmdb.org/t/p/$quality/$this"
}


// Original- 1998-11-19
// Target- Nov, 1998
fun String.getReleaseDate(): String {
    val originalFormat = SimpleDateFormat("yyyy-MM-dd").parse(this)
    val targetFormat = SimpleDateFormat("MMM, yyyy")

    return targetFormat.format(originalFormat!!)
}

// Original- 1998-11-19
// Target- 1998
@SuppressLint("SimpleDateFormat")
fun String.getReleaseYear(): String {
    val originalFormat = SimpleDateFormat("yyyy-MM-dd").parse(this)
    val targetFormat = SimpleDateFormat("yyyy")

    return targetFormat.format(originalFormat!!)
}

fun Double.getRating() = ((this.toFloat() * 5) / 10)

fun Double.getPopularity() = ((this.toInt() * 100) / 10).toString()

fun Int.getMovieDuration(): String {
    val startTime = "00:00"
    val hours = this / 60 + startTime.substring(0, 1).toInt()
    val mins = this % 60 + startTime.substring(3, 4).toInt()
    return "${hours}hrs ${mins}mins"
}

private val VerticalScrollConsumer = object : NestedScrollConnection {
    override fun onPreScroll(available: Offset, source: NestedScrollSource) = available.copy(x = 0f)
    override suspend fun onPreFling(available: Velocity) = available.copy(x = 0f)
}

private val HorizontalScrollConsumer = object : NestedScrollConnection {
    override fun onPreScroll(available: Offset, source: NestedScrollSource) = available.copy(y = 0f)
    override suspend fun onPreFling(available: Velocity) = available.copy(y = 0f)
}

fun Modifier.disabledVerticalPointerInputScroll(disabled: Boolean = true) =
    if (disabled) this.nestedScroll(VerticalScrollConsumer) else this

fun Modifier.disabledHorizontalPointerInputScroll(disabled: Boolean = true) =
    if (disabled) this.nestedScroll(HorizontalScrollConsumer) else this

package utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.vickikbt.shared.presentation.presenters.SharedSettingsPresenter
import koin

/**
 * Append the image url with string to determine the image quality to be loaded
 */
@Composable
fun String.loadImage(): String {
    val settingsRepository: SharedSettingsPresenter = koin.get()

    val quality = when (settingsRepository.selectedImageQuality.collectAsState().value) {
        0 -> "original"
        else -> "w500"
    }

    return "https://image.tmdb.org/t/p/$quality$this"
}

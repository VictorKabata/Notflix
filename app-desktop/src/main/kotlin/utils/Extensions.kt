package utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.vickikbt.shared.presentation.presenters.SharedSettingsPresenter
import koin

/**Append the image url with string to determine the image quality to be loaded*/
@Composable
fun String.loadImage(): String {
    val settingsRepository: SharedSettingsPresenter = koin.get()

    val quality = when (settingsRepository.selectedImageQuality.collectAsState().value) {
        0 -> "original"
        else -> "w500"
    }

    return "https://image.tmdb.org/t/p/$quality$this"
}

internal fun String.getNavArguments(): String {
    val regex = "^details/(\\d+)$".toRegex()

    val matchResult = regex.find(this)

    val result = matchResult?.groups?.get(1)?.value

    return if (!result.isNullOrEmpty()) result
    else throw Exception("Invalid arguments passed")
}

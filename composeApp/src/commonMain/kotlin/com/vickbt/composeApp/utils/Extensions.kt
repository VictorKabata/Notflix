package com.vickbt.composeApp.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.memory.MemoryCache
import coil3.request.CachePolicy
import coil3.request.crossfade
import coil3.util.DebugLogger
import com.vickbt.composeApp.ui.screens.settings.SettingsViewModel
import org.koin.compose.koinInject

/**
 * Append the image url with string to determine the image quality to be loaded
 */
@Composable
fun String.loadImage(): String {
    val settingsRepository: SettingsViewModel = koinInject()

    val quality =
        when (settingsRepository.settingsUiState.collectAsState().value.selectedImageQuality) {
            0 -> "original"
            else -> "w500"
        }

    return "https://image.tmdb.org/t/p/$quality/$this"
}

fun PlatformContext.getAsyncImageLoader() = ImageLoader.Builder(this)
    .crossfade(true)
    .memoryCachePolicy(CachePolicy.ENABLED)
    .memoryCache {
        MemoryCache.Builder().maxSizePercent(this, 0.3).strongReferencesEnabled(true).build()
    }
    .logger(DebugLogger())
    .build()

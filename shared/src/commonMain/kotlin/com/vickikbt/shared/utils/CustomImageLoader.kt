package com.vickikbt.shared.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import com.seiko.imageloader.ImageLoader
import com.seiko.imageloader.LocalImageLoader
import org.koin.compose.koinInject

expect class CustomImageLoader {
    fun generateImageLoader(): ImageLoader
}

@Composable
fun commonImageLoader(content: @Composable () -> Unit) {
    val customImageLoader: CustomImageLoader = koinInject()

    CompositionLocalProvider(
        LocalImageLoader provides remember { customImageLoader.generateImageLoader() }
    ) {
        content()
    }
}

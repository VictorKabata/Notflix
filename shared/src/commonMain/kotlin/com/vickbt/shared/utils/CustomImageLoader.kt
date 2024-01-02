package com.vickbt.shared.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import com.seiko.imageloader.ImageLoader
import com.seiko.imageloader.LocalImageLoader

expect object CustomImageLoader {
    fun generateImageLoader(): ImageLoader
}

@Composable
fun commonImageLoader(content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalImageLoader provides remember { CustomImageLoader.generateImageLoader() }
    ) {
        content()
    }
}

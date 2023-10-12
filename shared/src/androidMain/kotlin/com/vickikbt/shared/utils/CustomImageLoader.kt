package com.vickikbt.shared.utils

import com.seiko.imageloader.ImageLoader
import com.seiko.imageloader.cache.memory.maxSizePercent
import com.seiko.imageloader.component.setupDefaultComponents
import com.seiko.imageloader.option.androidContext
import okio.Path.Companion.toOkioPath

actual object CustomImageLoader {

    actual fun generateImageLoader(): ImageLoader {
        val context = ContextUtils.context

        return ImageLoader {
            options {
                androidContext(context)
            }
            components {
                setupDefaultComponents()
            }
            interceptor {
                memoryCacheConfig {
                    // Set the max size to 25% of the app's available memory.
                    maxSizePercent(context, 0.25)
                }
                diskCacheConfig {
                    directory(context.cacheDir.toOkioPath())
                    maxSizeBytes(512L * 1024 * 1024) // 512MB
                }
            }
        }
    }
}

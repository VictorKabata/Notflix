package com.vickikbt.shared.utils

import com.seiko.imageloader.ImageLoader
import com.seiko.imageloader.component.setupDefaultComponents
import okio.Path.Companion.toOkioPath
import java.io.File

actual object CustomImageLoader {

    actual fun generateImageLoader(): ImageLoader {
        return ImageLoader {
            components {
                setupDefaultComponents()
            }
            interceptor {
                memoryCacheConfig {
                    maxSizeBytes(32 * 1024 * 1024) // 32MB
                }
                diskCacheConfig {
                    directory(getCacheDir().toOkioPath().resolve("image_cache"))
                    maxSizeBytes(512L * 1024 * 1024) // 512MB
                }
            }
        }
    }

    private fun getCacheDir() = when (currentOperatingSystem) {
        OperatingSystem.Windows -> File(System.getenv("AppData"), "$applicationName/cache")
        OperatingSystem.Linux -> File(System.getProperty("user.home"), ".cache/$applicationName")
        OperatingSystem.MacOS -> File(
            System.getProperty("user.home"),
            "Library/Caches/$applicationName"
        )

        else -> throw IllegalStateException("Unsupported operating system")
    }

    private enum class OperatingSystem {
        Windows, Linux, MacOS, Unknown
    }

    private val currentOperatingSystem: OperatingSystem
        get() {
            val operSys = System.getProperty("os.name").lowercase()
            return if (operSys.contains("win")) {
                OperatingSystem.Windows
            } else if (operSys.contains("nix") || operSys.contains("nux") ||
                operSys.contains("aix")
            ) {
                OperatingSystem.Linux
            } else if (operSys.contains("mac")) {
                OperatingSystem.MacOS
            } else {
                OperatingSystem.Unknown
            }
        }

    private val applicationName = "Notflix"
}

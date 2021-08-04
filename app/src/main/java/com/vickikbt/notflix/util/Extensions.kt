package com.vickikbt.notflix.util

import com.vickikbt.cache.preferences.ImagesPreferences
import com.vickikbt.domain.utils.Constants
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


inline fun <reified T> getKoinInstance(): T {
    return object : KoinComponent {
        val value: T by inject()
    }.value
}

private val imagesPreferences: ImagesPreferences = getKoinInstance()

/**
 * Append the image url with string to determine the image quality to be loaded
 */
fun String.loadImage(): String {
    var imageQuality: String? = null
    imagesPreferences.imageQuality.observeForever {
        when (it) {
            "high_quality" -> {
                imageQuality = "${Constants.IMAGE_PREFIX}/original/$this"
            }
            "medium_quality" -> {
                imageQuality = "${Constants.IMAGE_PREFIX}/w500/$this"
            }
            "low_quality" -> {
                imageQuality = "${Constants.IMAGE_PREFIX}/w500/$this" //ToDo: Lower image quality value
            }else->imageQuality = "${Constants.IMAGE_PREFIX}/w500/$this"
        }
    }
    return imageQuality!!
}

/**
 * Converts bytes to megabytes
 */
fun Long.toMegabytes(): Int {
    return (this/1024/1024).toInt()
}
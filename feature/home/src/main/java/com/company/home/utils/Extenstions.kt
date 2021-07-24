package com.company.home.utils

import com.vickikbt.cache.preferences.ImagesPreferences
import com.vickikbt.domain.utils.Constants.IMAGE_PREFIX
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import timber.log.Timber

inline fun <reified T> getKoinInstance(): T {
    return object : KoinComponent {
        val value: T by inject()
    }.value
}

private val imagesPreferences: ImagesPreferences = getKoinInstance()

fun String.loadImage(): String {
    var imageQuality: String? = null
    imagesPreferences.imageQuality.observeForever {
        when (it) {
            "high_quality" -> {
                imageQuality = "$IMAGE_PREFIX/original/$this"
            }
            "medium_quality" -> {
                imageQuality = "$IMAGE_PREFIX/w500/$this"
            }
            "low_quality" -> {
                imageQuality = "$IMAGE_PREFIX/w500/$this" //ToDo: Lower image quality value
            }
        }
    }
    Timber.e("Image quality: $imageQuality")
    return imageQuality!!
}
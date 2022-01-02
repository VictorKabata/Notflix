package com.vickikbt.notflix.util

import android.annotation.SuppressLint
import android.view.View
import com.vickikbt.cache.preferences.ImagesPreferences
import com.vickikbt.domain.utils.Constants
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.text.SimpleDateFormat


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
            }//else->imageQuality = "${Constants.IMAGE_PREFIX}/w500/$this"
            else->imageQuality = "${Constants.IMAGE_PREFIX}/original/$this"
        }
    }
    return imageQuality!!
}

//Original- 1998-11-19
//Target- Nov, 1998
fun String.getReleaseDate(): String {
    val originalFormat = SimpleDateFormat("yyyy-MM-dd").parse(this)
    val targetFormat = SimpleDateFormat("MMM, yyyy")

    return targetFormat.format(originalFormat!!)
}

//Original- 1998-11-19
//Target- 1998
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
    return "${hours}hrs : ${mins}mins"
}
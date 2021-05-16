package com.vickikbt.data.util

import android.annotation.SuppressLint
import com.vickikbt.data.util.Constants.IMAGE_PREFIX
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.regex.Pattern

object DataFormatter {

    fun loadImage(imageUrl: String?) = "$IMAGE_PREFIX/original/$imageUrl"

    //Original- 1998-11-19
    //Target- Nov, 1998
    @SuppressLint("SimpleDateFormat")
    fun getReleaseDate(date: String?): String {
        val originalFormat = SimpleDateFormat("yyyy-MM-dd").parse(date!!)
        val targetFormat = SimpleDateFormat("MMM, yyyy")

        return targetFormat.format(originalFormat!!)
    }


    //Original- 1998-11-19
    //Target- 1998
    @SuppressLint("SimpleDateFormat")
    fun getReleaseYear(date: String?): String {
        val originalFormat = SimpleDateFormat("yyyy-MM-dd").parse(date!!)
        val targetFormat = SimpleDateFormat("yyyy")

        val releaseYear = targetFormat.format(originalFormat!!)

        Timber.e("Release Year: $releaseYear")

        return releaseYear
    }

    fun getRating(rating: Double?) = (rating!!.toFloat() * 5) / 10

    fun getPopularity(rating: Double) = ((rating.toInt() * 100) / 10).toString()

    fun getMovieDuration(time: Int): String {
        val startTime = "00:00"
        val hours = time / 60 + startTime.substring(0, 1).toInt()
        val mins = time % 60 + startTime.substring(3, 4).toInt()
        return "${hours}hrs : ${mins}mins"
    }

    fun getYoutubeVideoFromUrl(inUrl: String): String? {
        if (inUrl.toLowerCase().contains("youtu.eb")) {
            return inUrl.substring(inUrl.lastIndexOf("/") + 1)
        }
        val pattern = "(?<=watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*"
        val compiledPattern = Pattern.compile(pattern)
        val matcher = compiledPattern.matcher(inUrl)
        return if (matcher.find()) matcher.group() else null
    }

}
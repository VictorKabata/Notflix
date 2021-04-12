package com.vickikbt.data.util

import android.annotation.SuppressLint
import com.vickikbt.data.util.Constants.IMAGE_PREFIX
import java.text.SimpleDateFormat

object DataFormatter {

    fun loadImage(imageUrl: String) = "$IMAGE_PREFIX/original/$imageUrl"

    //Original- 1998-11-19
    //Target- Nov, 1998.
    @SuppressLint("SimpleDateFormat")
    fun dateFormatter(date: String): String {
        val originalFormat = SimpleDateFormat("yyyy-MM-dd").parse(date)
        val targetFormat = SimpleDateFormat("MMM, yyyy")

        return targetFormat.format(originalFormat!!)
    }

    fun getRating(rating: Double) = (rating.toFloat() * 5) / 10

}
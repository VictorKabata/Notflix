package com.vickbt.composeApp.utils

import kotlinx.datetime.toLocalDate

/**
 * Returns the same string with each starting letter of words capitalized
 *
 * eg. THE BIG brown wolf jumped Over => The Big Brown Wolf Jumped Over
 */
fun String.capitalizeEachWord(): String {
    return lowercase().split(" ").joinToString(" ") { firstCharacter ->
        firstCharacter.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase() else it.toString()
        }
    }
}

/**Returns formatted date for movie release date*/
fun String?.getReleaseDate(): String {
    val localDate = this?.toLocalDate()

    return "${localDate?.dayOfMonth} ${localDate?.month}, ${localDate?.year}"
}

/**Converts movie duration from minutes to hours and minutes
 * eg. 150 minutes => 1hr 30mins
 * eg. 250 minutes => 4hrs 10mins
 * */
fun Int?.getMovieDuration(): String? {
    return if (this != null) {
        val hours = (this / 60)
        val minutes = this % 60

        val runtime = if (hours <= 1) "${hours}hr ${minutes}mins" else "${hours}hrs ${minutes}mins"

        runtime
    } else {
        null
    }
}

/**Convert movie rating to a value out of 100% eg. 8 => 80% */
fun Double.getPopularity(): String {
    return ((this.toInt() * 100) / 10).toString()
}

/**Convert movie rating to a value out of 5.0 eg. 8 => 4.0/5.0 */
fun Double.getRating(): String {
    val byTwo = this / 2
    val before = byTwo.toString().substringBefore(".")
    val after = byTwo.toString().substringAfter(".").split("")[1]
    return "$before.$after"
}

fun Int.toBoolean(): Boolean {
    return this != 0
}

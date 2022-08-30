package com.vickikbt.shared.utils

import com.vickikbt.shared.presentation.presenters.SharedSettingsPresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.toLocalDate
import org.koin.core.component.KoinComponent

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
fun String?.getReleaseDate(): String? {
    val localDate = this?.toLocalDate()

    return "${localDate?.dayOfMonth} ${localDate?.month}, ${localDate?.year}"
}

fun Int?.getMovieDuration(): String? {
    return if (this != null) {
        val hours = (this / 60)
        val minutes = this % 60

        val runtime = "${hours}hrs ${minutes}mins"

        runtime
    } else null
}

fun Double.getPopularity(): String {
    return ((this.toInt() * 100) / 10).toString()
}

fun Double.getRating(): String {
    val byTwo = this / 2
    val before = byTwo.toString().substringBefore(".")
    val after = byTwo.toString().substringAfter(".").split("")[1]
    return "$before.$after"
}

fun KoinComponent.viewModelScope(operation: suspend () -> Unit) {
    val viewModelScope = CoroutineScope(Dispatchers.Default)
    val supervisorJob = MutableStateFlow<Job?>(null)

    val job = viewModelScope.launch { operation.invoke() }

    supervisorJob.value = job
    job.invokeOnCompletion {
        supervisorJob.value?.cancel()
        supervisorJob.value = null
    }
}

fun getAppLanguage(settingsPresenter: SharedSettingsPresenter): String {

    return when (settingsPresenter.selectedLanguage.value) {
        0 -> "en"
        1 -> "es"
        2 -> "fr"
        3 -> "de"
        else -> "en"
    }
}

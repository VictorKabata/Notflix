package com.vickikbt.notflix.util

import android.content.Context
import java.util.*

class LocaleManager {

    fun setLocale(context: Context, language: String) {
        val locale = Locale(language)

        val resources = context.resources
        val configuration = resources.configuration

        configuration.locale = locale

        resources.updateConfiguration(configuration, resources.displayMetrics)
    }
}

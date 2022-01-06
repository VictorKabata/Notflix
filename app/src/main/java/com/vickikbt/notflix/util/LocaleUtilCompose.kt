package com.vickikbt.notflix.util

import android.content.Context
import timber.log.Timber
import java.util.*

class LocaleUtilCompose constructor(private val context: Context) {

    fun changeLocale(language:String){
        val locale=Locale(language)
        val resources=context.resources
        val configuration=resources.configuration
        configuration.setLocale(Locale.FRANCE)
        resources.updateConfiguration(configuration, resources.displayMetrics)
        Timber.e("Set locale: $language")
    }

}
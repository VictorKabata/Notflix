package com.vickikbt.notflix.util

import android.content.Context
import android.os.Build
import timber.log.Timber
import java.util.*

class LocaleManager constructor(private val context: Context) {

    fun setLocale(context: Context?): Context {
        return setNewLocale(Locale.getDefault().language)
    }

    fun setNewLocale(language: String): Context {
        Timber.e("Setting new locale: $language")
        return updateResources(language)
    }

    private fun updateResources(language: String): Context {
        var ctx = context

        val locale = Locale(language)
        Locale.setDefault(locale)

        val resources = context.resources
        val configurations = resources.configuration

        if (Build.VERSION.SDK_INT >= 17) {
            configurations.setLocale(locale)
            ctx = context.createConfigurationContext(configurations)
        } else {
            configurations.setLocale(locale)
            resources.updateConfiguration(configurations, resources.displayMetrics)
        }

        return ctx
    }
}
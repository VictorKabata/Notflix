package com.vickikbt.notflix.util

import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import android.os.LocaleList
import java.util.*

class LocaleUtil constructor(baseContext: Context) : ContextWrapper(baseContext) {

        fun updateLocale(applicationContext: Context, locale: Locale): LocaleUtil {
            var context = applicationContext
            val resources = context.resources
            val configuration = resources.configuration

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                val localeList = LocaleList(locale)
                LocaleList.setDefault(localeList)
                configuration.setLocales(localeList)
            } else {
                configuration.setLocale(locale)
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
                context = context.createConfigurationContext(configuration)
            } else {
                resources.updateConfiguration(configuration, resources.displayMetrics)
            }

            
            return LocaleUtil(context)
        }

}
package com.vickbt.composeApp.utils

import android.content.Context

object ContextUtils {

    private var kmpApplicationContext: Context? = null

    val context
        get() = kmpApplicationContext
            ?: error("Android context has not been set. Please call setContext in your Application's onCreate.")

    fun setContext(context: Context) {
        kmpApplicationContext = context.applicationContext
    }
}

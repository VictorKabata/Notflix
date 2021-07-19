package com.vickikbt.cache.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.preference.PreferenceManager

class ThemePreferences constructor(private val context: Context) {

    private val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    private val _appTheme: String?
        get() = sharedPreferences.getString("theme", "default_value")

    private val _appThemeMutableLiveData: MutableLiveData<String> = MutableLiveData()
    val appTheme: LiveData<String>
        get() = _appThemeMutableLiveData

    private val preferenceChangedListener =
        SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            when (key) {
                "theme" -> {
                    _appThemeMutableLiveData.value = _appTheme
                }
            }
        }

    init {
        _appThemeMutableLiveData.value = _appTheme
        sharedPreferences.registerOnSharedPreferenceChangeListener(preferenceChangedListener)
    }


}
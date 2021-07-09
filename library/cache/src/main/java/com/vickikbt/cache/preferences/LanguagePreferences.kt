package com.vickikbt.cache.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.preference.PreferenceManager

class LanguagePreferences constructor(private val context: Context) {

    private val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    private val _appLanguage: String?
        get() = sharedPreferences.getString("language", "default_value")

    private val _appLanguageMutableLiveData: MutableLiveData<String> = MutableLiveData()
    val appLanguage: LiveData<String>
        get() = _appLanguageMutableLiveData

    private val preferenceChangedListener =
        SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            when (key) {
                "language" -> {
                    _appLanguageMutableLiveData.value = _appLanguage
                }
            }
        }

    init {
        _appLanguageMutableLiveData.value = _appLanguage
        sharedPreferences.registerOnSharedPreferenceChangeListener(preferenceChangedListener)
    }


}
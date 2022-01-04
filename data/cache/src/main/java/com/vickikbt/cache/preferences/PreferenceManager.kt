package com.vickikbt.cache.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vickikbt.domain.utils.Constants

class PreferenceManager constructor(private val context: Context) {

    private val preferences: SharedPreferences =
        context.getSharedPreferences("notflix_preferences", Context.MODE_PRIVATE)

    fun setString(key: String, value: String?) {
        preferences.edit {
            putString(key, value)
        }
    }

    fun getString(key: String, defaultValue: String?) = preferences.getString(key, defaultValue)

    fun setLong(key: String, value: Long) {
        preferences.edit {
            putLong(key, value)
        }
    }

    fun getLong(key: String, defaultValue: Long) = preferences.getLong(key, defaultValue)

    private val _appTheme: String?
        get() = getString(
            Constants.KEY_THEME,
            Constants.SYSTEM_THEME
        )

    private val _appLanguage: String?
        get() = getString(
            Constants.KEY_LANGUAGE,
            defaultValue = "No Russian"
        )

    private val _imageQuality: String?
        get() = getString(
            Constants.KEY_IMAGE_QUALITY,
            defaultValue = "${Constants.IMAGE_PREFIX}/w500/$this"
        )

    private val _appThemeMutableLiveData: MutableLiveData<String> = MutableLiveData()
    val appTheme: LiveData<String> get() = _appThemeMutableLiveData

    private val _appLanguageMutableLiveData: MutableLiveData<String> = MutableLiveData()
    val appLanguage: LiveData<String> get() = _appLanguageMutableLiveData

    private val _imageQualityMutableLiveData: MutableLiveData<String> = MutableLiveData()
    val imageQuality: LiveData<String> get() = _imageQualityMutableLiveData

    private val preferenceChangedListener =
        SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            when (key) {
                Constants.KEY_THEME -> {
                    _appThemeMutableLiveData.value = _appTheme
                }

                Constants.KEY_LANGUAGE -> {
                    _appLanguageMutableLiveData.value = _appLanguage
                }

                Constants.KEY_IMAGE_QUALITY -> {
                    _imageQualityMutableLiveData.value = _imageQuality
                }
            }
        }

    init {
        _appThemeMutableLiveData.value = _appTheme
        _appLanguageMutableLiveData.value = _appLanguage
        _imageQualityMutableLiveData.value = _imageQuality
        preferences.registerOnSharedPreferenceChangeListener(preferenceChangedListener)
    }

}
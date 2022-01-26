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

    fun setInt(key: String, value: Int) {
        preferences.edit {
            putInt(key, value)
        }
    }

    fun getInt(key: String, defaultValue: Int = 0) = preferences.getInt(key, defaultValue)

    fun getLong(key: String, defaultValue: Long) = preferences.getLong(key, defaultValue)

    private val _appTheme: Int
        get() = getInt(Constants.KEY_THEME)

    private val _appLanguage: Int
        get() = getInt(Constants.KEY_LANGUAGE)

    private val _imageQuality: Int
        get() = getInt(Constants.KEY_IMAGE_QUALITY)

    private val _appThemeMutableLiveData: MutableLiveData<Int> = MutableLiveData()
    val appTheme: LiveData<Int> get() = _appThemeMutableLiveData

    private val _appLanguageMutableLiveData: MutableLiveData<Int> = MutableLiveData()
    val appLanguage: LiveData<Int> get() = _appLanguageMutableLiveData

    private val _imageQualityMutableLiveData: MutableLiveData<Int> = MutableLiveData()
    val imageQuality: LiveData<Int> get() = _imageQualityMutableLiveData

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
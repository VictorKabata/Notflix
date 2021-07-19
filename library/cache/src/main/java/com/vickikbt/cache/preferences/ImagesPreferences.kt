package com.vickikbt.cache.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.preference.PreferenceManager

class ImagesPreferences constructor(private val context: Context) {

    private val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    private val _imageQuality: String?
        get() = sharedPreferences.getString("image_quality", "default_value")

    private val _imageQualityMutableLiveData: MutableLiveData<String> = MutableLiveData()
    val imageQuality: LiveData<String>
        get() = _imageQualityMutableLiveData

    private val preferenceChangedListener =
        SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            when (key) {
                "image_quality" -> {
                    _imageQualityMutableLiveData.value = _imageQuality
                }
            }
        }

    init {
        _imageQualityMutableLiveData.value = _imageQuality
        sharedPreferences.registerOnSharedPreferenceChangeListener(preferenceChangedListener)
    }


}
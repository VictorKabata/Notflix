package com.vickikbt.settings.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.vickikbt.cache.preferences.ImagesPreferences
import com.vickikbt.settings.R
import org.koin.android.ext.android.inject
import timber.log.Timber

class SettingsFragment : PreferenceFragmentCompat(), PreferenceManager.OnPreferenceTreeClickListener {

    private val imagesPreferences: ImagesPreferences by inject()

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings_fragment, rootKey)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imagesPreferences.imageQuality.observeForever {
            Timber.e("Image quality: $it")
        }
    }

    override fun onPreferenceTreeClick(preference: Preference?): Boolean {
        val preferenceKey=preference?.key

        //when(preference)


        return super.onPreferenceTreeClick(preference)
    }

}
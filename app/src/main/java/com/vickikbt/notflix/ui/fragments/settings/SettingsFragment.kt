package com.vickikbt.notflix.ui.fragments.settings

import android.os.Bundle
import android.view.View
import androidx.preference.PreferenceFragmentCompat
import com.vickikbt.cache.preferences.ImagesPreferences
import com.vickikbt.notflix.R
import org.koin.android.ext.android.inject
import timber.log.Timber

class SettingsFragment : PreferenceFragmentCompat() {

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

}
package com.vickikbt.notflix.ui.fragments.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.vickikbt.notflix.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings_fragment, rootKey)
    }
}
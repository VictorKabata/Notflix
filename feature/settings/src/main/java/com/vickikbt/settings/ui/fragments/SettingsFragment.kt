package com.vickikbt.settings.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.vickikbt.cache.preferences.ImagesPreferences
import com.vickikbt.domain.utils.Constants.BUG_REPORT_EMAIL
import com.vickikbt.domain.utils.Constants.SOURCE_CODE_URL
import com.vickikbt.notflix.util.toast
import com.vickikbt.settings.R
import org.koin.android.ext.android.inject
import timber.log.Timber


class SettingsFragment : PreferenceFragmentCompat(),
    PreferenceManager.OnPreferenceTreeClickListener {

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
        when (preference?.key) {
            "language" -> {
                requireContext().toast("Under Development")
            }

            "report bug" -> {
                reportBug()
            }

            "source code" -> {
                openSourceCode()
            }
        }

        return super.onPreferenceTreeClick(preference)
    }

    private fun reportBug() {
        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
            type = "text/plain"
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf(BUG_REPORT_EMAIL))
            putExtra(Intent.EXTRA_SUBJECT, BUG_REPORT_EMAIL)
        }

        startActivity(emailIntent)
    }

    private fun openSourceCode() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(SOURCE_CODE_URL)
        startActivity(intent)
    }

}
package com.vickikbt.notflix.ui.screens.settings

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vickikbt.notflix.R
import com.vickikbt.notflix.ui.components.appbars.AppBar
import com.vickikbt.notflix.ui.components.preferences.DialogPreferenceSelection
import com.vickikbt.notflix.ui.components.preferences.PreferencesGroup
import com.vickikbt.notflix.ui.components.preferences.TextPreference
import com.vickikbt.shared.data.cache.multiplatformsettings.PreferenceManager.Companion.IMAGE_QUALITY_KEY
import com.vickikbt.shared.data.cache.multiplatformsettings.PreferenceManager.Companion.LANGUAGE_KEY
import com.vickikbt.shared.data.cache.multiplatformsettings.PreferenceManager.Companion.THEME_KEY
import com.vickikbt.shared.domain.utils.Constants
import com.vickikbt.shared.presentation.presenters.SharedSettingsPresenter
import org.koin.androidx.compose.get

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SettingsScreen(navController: NavController, viewModel: SharedSettingsPresenter = get()) {
    val context = LocalContext.current

    val currentTheme = viewModel.selectedTheme.collectAsState().value ?: 0
    val currentLanguage = viewModel.selectedLanguage.collectAsState().value ?: 0
    val currentImageQuality = viewModel.selectedImageQuality.collectAsState().value ?: 0

    val showThemeDialog = remember { mutableStateOf(false) }
    val showLanguageDialog = remember { mutableStateOf(false) }
    val showImageQualityDialog = remember { mutableStateOf(false) }

    val themeLabel = stringArrayResource(id = R.array.theme_labels)[currentTheme]
    val languageLabel = stringArrayResource(id = R.array.language_labels)[currentLanguage]
    val imageQualityLabel =
        stringArrayResource(id = R.array.image_quality_labels)[currentImageQuality]

    Scaffold(topBar = { AppBar(stringResource(id = R.string.title_settings)) }) {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.surface) {
            Column {
                Spacer(modifier = Modifier.height(8.dp))

                PreferencesGroup(title = stringResource(id = R.string.title_personalisation)) {
                    TextPreference(
                        icon = painterResource(id = R.drawable.ic_theme),
                        title = stringResource(id = R.string.change_theme),
                        subTitle = themeLabel,
                        onClick = { showThemeDialog.value = !showThemeDialog.value }
                    )

                    if (showThemeDialog.value) ChangeTheme(
                        viewModel = viewModel,
                        showDialog = showThemeDialog,
                        currentValue = themeLabel
                    )

                    TextPreference(
                        icon = painterResource(id = R.drawable.ic_language),
                        title = stringResource(id = R.string.change_language),
                        subTitle = languageLabel,
                        onClick = { showLanguageDialog.value = !showLanguageDialog.value }
                    )

                    if (showLanguageDialog.value) ChangeLanguage(
                        viewModel = viewModel,
                        showDialog = showLanguageDialog,
                        currentValue = languageLabel
                    )

                    TextPreference(
                        icon = painterResource(id = R.drawable.ic_image_quality),
                        title = stringResource(id = R.string.change_image_quality),
                        subTitle = imageQualityLabel,
                        onClick = { showImageQualityDialog.value = !showImageQualityDialog.value }
                    )

                    if (showImageQualityDialog.value) ChangeImageQuality(
                        viewModel = viewModel,
                        showDialog = showImageQualityDialog,
                        currentValue = imageQualityLabel
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                PreferencesGroup(
                    title = stringResource(id = R.string.title_extras),
                    isLast = true
                ) {
                    TextPreference(
                        icon = painterResource(id = R.drawable.ic_report_bug),
                        title = stringResource(id = R.string.report_bug),
                        subTitle = stringResource(id = R.string.report_bug_description),
                        onClick = { reportBug(context) }
                    )

                    TextPreference(
                        modifier = Modifier.clickable { },
                        icon = painterResource(id = R.drawable.ic_github),
                        title = stringResource(id = R.string.source_code),
                        subTitle = stringResource(id = R.string.source_code_description),
                        onClick = { openSourceCode(context) }
                    )
                }
            }
        }
    }
}

@Composable
private fun ChangeTheme(
    viewModel: SharedSettingsPresenter,
    showDialog: MutableState<Boolean>,
    currentValue: String?
) {
    DialogPreferenceSelection(
        showDialog = showDialog.value,
        title = stringResource(id = R.string.change_theme),
        currentValue = currentValue ?: stringResource(id = R.string.def),
        labels = stringArrayResource(id = R.array.theme_labels),
        onNegativeClick = { showDialog.value = false }
    ) { theme ->
        viewModel.savePreferenceSelection(key = THEME_KEY, selection = theme)
    }
}

@Composable
private fun ChangeLanguage(
    viewModel: SharedSettingsPresenter,
    showDialog: MutableState<Boolean>,
    currentValue: String?
) {
    DialogPreferenceSelection(
        showDialog = showDialog.value,
        title = stringResource(id = R.string.change_language),
        currentValue = currentValue ?: stringResource(id = R.string.language_eg),
        labels = stringArrayResource(id = R.array.language_labels),
        onNegativeClick = { showDialog.value = false }
    ) { language ->
        viewModel.savePreferenceSelection(key = LANGUAGE_KEY, selection = language)
    }
}

@Composable
private fun ChangeImageQuality(
    viewModel: SharedSettingsPresenter,
    showDialog: MutableState<Boolean>,
    currentValue: String?
) {
    DialogPreferenceSelection(
        showDialog = showDialog.value,
        title = stringResource(id = R.string.change_image_quality),
        currentValue = currentValue ?: stringResource(id = R.string.def),
        labels = stringArrayResource(id = R.array.image_quality_labels),
        onNegativeClick = { showDialog.value = false }
    ) { imageQuality ->
        viewModel.savePreferenceSelection(
            key = IMAGE_QUALITY_KEY,
            selection = imageQuality
        )
    }
}

private fun reportBug(context: Context) {
    val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
        type = "text/plain"
        data = Uri.parse("mailto:")
        putExtra(Intent.EXTRA_EMAIL, arrayOf(Constants.BUG_REPORT_EMAIL))
        putExtra(Intent.EXTRA_SUBJECT, Constants.BUG_REPORT_SUBJECT)
    }

    context.startActivity(emailIntent)
}

private fun openSourceCode(context: Context) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(Constants.SOURCE_CODE_URL)
    context.startActivity(intent)
}

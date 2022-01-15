package com.vickikbt.notflix.ui.screens.settings

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vickikbt.domain.utils.Constants
import com.vickikbt.notflix.R
import com.vickikbt.notflix.ui.components.AppBar
import com.vickikbt.notflix.ui.components.preferences.DialogPreferenceSelection
import com.vickikbt.notflix.ui.components.preferences.PreferencesGroup
import com.vickikbt.notflix.ui.components.preferences.TextPreference
import com.vickikbt.notflix.util.LocaleManager
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel

@Composable
fun SettingsScreen(navController: NavController, viewModel: SettingsViewModel = getViewModel()) {

    val context = LocalContext.current

    val currentTheme = viewModel.selectedTheme.observeAsState().value
    val currentLanguage = viewModel.selectedLanguage.observeAsState().value
    val currentImageQuality = viewModel.selectedImageQuality.observeAsState().value

    val showThemeDialog = remember { mutableStateOf(false) }
    val showLanguageDialog = remember { mutableStateOf(false) }
    val showImageQualityDialog = remember { mutableStateOf(false) }

    Scaffold(topBar = { AppBar(stringResource(id = R.string.title_settings)) }) {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.surface) {
            Column {
                Spacer(modifier = Modifier.height(8.dp))

                PreferencesGroup(title = stringResource(id = R.string.title_personalisation)) {
                    TextPreference(
                        icon = painterResource(id = R.drawable.ic_theme),
                        title = stringResource(id = R.string.change_theme),
                        subTitle = currentTheme ?: stringResource(id = R.string.def),
                        onClick = { showThemeDialog.value = !showThemeDialog.value }
                    )

                    if (showThemeDialog.value) ChangeTheme(
                        viewModel = viewModel,
                        showDialog = showThemeDialog,
                        currentValue = currentTheme
                    )

                    TextPreference(
                        icon = painterResource(id = R.drawable.ic_language),
                        title = stringResource(id = R.string.change_language),
                        subTitle = currentLanguage,
                        onClick = { showLanguageDialog.value = !showLanguageDialog.value }
                    )

                    if (showLanguageDialog.value) ChangeLanguage(
                        viewModel = viewModel,
                        showDialog = showLanguageDialog,
                        currentValue = currentLanguage
                    )

                    TextPreference(
                        icon = painterResource(id = R.drawable.ic_image_quality),
                        title = stringResource(id = R.string.change_image_quality),
                        subTitle = currentImageQuality,
                        onClick = { showImageQualityDialog.value = !showImageQualityDialog.value }
                    )

                    if (showImageQualityDialog.value) ChangeImageQuality(
                        viewModel = viewModel,
                        showDialog = showImageQualityDialog,
                        currentValue = currentImageQuality
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
    viewModel: SettingsViewModel,
    showDialog: MutableState<Boolean>,
    currentValue: String?
) {
    DialogPreferenceSelection(
        showDialog = showDialog.value,
        title = stringResource(id = R.string.change_theme),
        currentValue = currentValue ?: stringResource(id = R.string.def),
        options = stringArrayResource(id = R.array.theme_options),
        onNegativeClick = { showDialog.value = false }) { theme ->
        viewModel.savePreferenceSelection(key = Constants.KEY_THEME, selection = theme)
    }
}

@Composable
private fun ChangeLanguage(
    viewModel: SettingsViewModel,
    showDialog: MutableState<Boolean>,
    currentValue: String?
) {
    DialogPreferenceSelection(
        showDialog = showDialog.value,
        title = stringResource(id = R.string.change_language),
        currentValue = currentValue ?: stringResource(id = R.string.def),
        options = stringArrayResource(id = R.array.language_options),
        onNegativeClick = { showDialog.value = false }
    ) { language ->
        viewModel.savePreferenceSelection(key = Constants.KEY_LANGUAGE, selection = language)
    }
}

@Composable
private fun ChangeImageQuality(
    viewModel: SettingsViewModel,
    showDialog: MutableState<Boolean>,
    currentValue: String?
) {
    DialogPreferenceSelection(
        showDialog = showDialog.value,
        title = stringResource(id = R.string.change_image_quality),
        currentValue = currentValue ?: stringResource(id = R.string.def),
        options = stringArrayResource(id = R.array.image_quality_options),
        onNegativeClick = { showDialog.value = false }) { imageQuality ->
        viewModel.savePreferenceSelection(
            key = Constants.KEY_IMAGE_QUALITY,
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

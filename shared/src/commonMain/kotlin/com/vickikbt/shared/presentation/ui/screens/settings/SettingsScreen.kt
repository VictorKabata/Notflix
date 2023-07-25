package com.vickikbt.shared.presentation.ui.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vickikbt.shared.domain.utils.Constants.KEY_IMAGE_QUALITY
import com.vickikbt.shared.domain.utils.Constants.KEY_LANGUAGE
import com.vickikbt.shared.domain.utils.Constants.KEY_THEME
import com.vickikbt.shared.presentation.ui.components.appBars.AppBar
import com.vickikbt.shared.presentation.ui.components.preferences.DialogPreferenceSelection
import com.vickikbt.shared.presentation.ui.components.preferences.PreferencesGroup
import com.vickikbt.shared.presentation.ui.components.preferences.TextPreference
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SettingsScreen(viewModel: SettingsViewModel = koinInject()) {
    LaunchedEffect(key1 = viewModel) {
        viewModel.getThemePreference()
        viewModel.getLanguagePreference()
        viewModel.getImageQualityPreference()
    }

    val settingsUiState = viewModel.settingsUiState.collectAsState().value

    val showThemeDialog = remember { mutableStateOf(false) }
    val showLanguageDialog = remember { mutableStateOf(false) }
    val showImageQualityDialog = remember { mutableStateOf(false) }

    val themeLabels = listOf("Dark", "Light", "System Default")
    val languageLabels = listOf("English", "French")
    val imageQualityLabels = listOf("High Quality", "Low Quality")

    val themeLabel = themeLabels[settingsUiState.selectedTheme]
    val languageLabel = languageLabels[settingsUiState.selectedLanguage]
    val imageQualityLabel = imageQualityLabels[settingsUiState.selectedImageQuality]

    Scaffold(topBar = { AppBar("Settings") }) {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.surface) {
            Column {
                Spacer(modifier = Modifier.height(8.dp))

                PreferencesGroup(title = "Personalisation") {
                    TextPreference(
                        icon = painterResource("drawables/ic_theme.xml"),
                        title = "Change theme",
                        subTitle = themeLabel,
                        onClick = { showThemeDialog.value = !showThemeDialog.value }
                    )

                    if (showThemeDialog.value) ChangeTheme(
                        viewModel = viewModel,
                        showDialog = showThemeDialog,
                        currentValue = themeLabel
                    )

                    TextPreference(
                        icon = painterResource("drawables/ic_language.xml"),
                        title = "Change language",
                        subTitle = languageLabel,
                        onClick = { showLanguageDialog.value = !showLanguageDialog.value }
                    )

                    if (showLanguageDialog.value) ChangeLanguage(
                        viewModel = viewModel,
                        showDialog = showLanguageDialog,
                        currentValue = languageLabel
                    )

                    TextPreference(
                        icon = painterResource("drawables/ic_image_quality.xml"),
                        title = "Image quality",
                        subTitle = imageQualityLabel,
                        onClick = { showImageQualityDialog.value = !showImageQualityDialog.value }
                    )

                    if (showImageQualityDialog.value) ChangeImageQuality(
                        viewModel = viewModel,
                        showDialog = showImageQualityDialog,
                        currentValue = imageQualityLabel
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
        title = "Change theme",
        currentValue = currentValue ?: "Default",
        labels = listOf(),
        onNegativeClick = { showDialog.value = false }
    ) { theme ->
        viewModel.savePreferenceSelection(key = KEY_THEME, selection = theme)
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
        title = "Change language",
        currentValue = currentValue ?: "EN",
        labels = listOf(),
        onNegativeClick = { showDialog.value = false }
    ) { language ->
        viewModel.savePreferenceSelection(key = KEY_LANGUAGE, selection = language)
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
        title = "Image quality",
        currentValue = currentValue ?: "Default",
        labels = listOf(),
        onNegativeClick = { showDialog.value = false }
    ) { imageQuality ->
        viewModel.savePreferenceSelection(
            key = KEY_IMAGE_QUALITY,
            selection = imageQuality
        )
    }
}

private fun reportBug() {}

private fun openSourceCode() {}

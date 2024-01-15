package com.vickbt.shared.ui.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Image
import androidx.compose.material.icons.rounded.Lightbulb
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.vickbt.shared.domain.utils.Constants.KEY_IMAGE_QUALITY
import com.vickbt.shared.domain.utils.Constants.KEY_THEME
import com.vickbt.shared.ui.components.appbars.AppBar
import com.vickbt.shared.ui.components.preferences.DialogPreferenceSelection
import com.vickbt.shared.ui.components.preferences.PreferencesGroup
import com.vickbt.shared.ui.components.preferences.TextPreference
import org.koin.compose.koinInject

private val themeLabels = listOf("Light", "Dark", "System Default")
private val imageQualityLabels = listOf("High Quality", "Low Quality")

@Composable
fun SettingsScreen(viewModel: SettingsViewModel = koinInject()) {
    val settingsUiState = viewModel.settingsUiState.collectAsState().value

    val showThemeDialog = remember { mutableStateOf(false) }
    val showImageQualityDialog = remember { mutableStateOf(false) }

    val themeLabel = themeLabels[settingsUiState.selectedTheme]
    val imageQualityLabel = imageQualityLabels[settingsUiState.selectedImageQuality]

    Scaffold(
        topBar = { AppBar("Settings") },
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            PreferencesGroup(title = "Personalisation") {
                TextPreference(
                    icon = Icons.Rounded.Lightbulb,
                    title = "Change theme",
                    subTitle = themeLabel,
                    onClick = { showThemeDialog.value = !showThemeDialog.value }
                )

                if (showThemeDialog.value) {
                    ChangeTheme(
                    viewModel = viewModel,
                    showDialog = showThemeDialog,
                    currentValue = themeLabel
                )
                }

                TextPreference(
                    icon = Icons.Rounded.Image,
                    title = "Image quality",
                    subTitle = imageQualityLabel,
                    onClick = { showImageQualityDialog.value = !showImageQualityDialog.value }
                )

                if (showImageQualityDialog.value) {
                    ChangeImageQuality(
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
        labels = themeLabels,
        onNegativeClick = { showDialog.value = false }
    ) { theme ->
        viewModel.savePreferenceSelection(key = KEY_THEME, selection = theme)
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
        labels = imageQualityLabels,
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

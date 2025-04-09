@file:OptIn(KoinExperimentalAPI::class)

package com.vickbt.composeApp.ui.screens.settings

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
import com.vickbt.composeApp.domain.utils.Constants.KEY_IMAGE_QUALITY
import com.vickbt.composeApp.domain.utils.Constants.KEY_THEME
import com.vickbt.composeApp.ui.components.appbars.AppBar
import com.vickbt.composeApp.ui.components.preferences.DialogPreferenceSelection
import com.vickbt.composeApp.ui.components.preferences.PreferencesGroup
import com.vickbt.composeApp.ui.components.preferences.TextPreference
import com.vickbt.shared.resources.Res
import com.vickbt.shared.resources.change_image_quality
import com.vickbt.shared.resources.change_theme
import com.vickbt.shared.resources.def
import com.vickbt.shared.resources.image_qualities
import com.vickbt.shared.resources.themes
import com.vickbt.shared.resources.title_personalisation
import com.vickbt.shared.resources.title_settings
import org.jetbrains.compose.resources.stringArrayResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = koinViewModel<SettingsViewModel>(),
) {
    val settingsUiState = viewModel.settingsUiState.collectAsState().value

    val themeLabels = stringArrayResource(Res.array.themes)
    val imageQualityLabels = stringArrayResource(Res.array.image_qualities)

    val showThemeDialog = remember { mutableStateOf(false) }
    val showImageQualityDialog = remember { mutableStateOf(false) }

    val themeLabel = themeLabels[settingsUiState.selectedTheme]
    val imageQualityLabel = imageQualityLabels[settingsUiState.selectedImageQuality]

    Scaffold(
        modifier = Modifier,
        topBar = { AppBar(title = stringResource(Res.string.title_settings)) },
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            PreferencesGroup(title = stringResource(Res.string.title_personalisation)) {
                TextPreference(
                    icon = Icons.Rounded.Lightbulb,
                    title = stringResource(Res.string.change_theme),
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
                    title = stringResource(Res.string.change_image_quality),
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
        title = stringResource(Res.string.change_theme),
        currentValue = currentValue ?: "Default",
        labels = stringArrayResource(Res.array.themes),
        onDismissRequest = { showDialog.value = false }
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
        title = stringResource(Res.string.change_image_quality),
        currentValue = currentValue ?: stringResource(Res.string.def),
        labels = stringArrayResource(Res.array.image_qualities),
        onDismissRequest = { showDialog.value = false }
    ) { imageQuality ->
        viewModel.savePreferenceSelection(
            key = KEY_IMAGE_QUALITY,
            selection = imageQuality
        )
    }
}

private fun reportBug() {}

private fun openSourceCode() {}

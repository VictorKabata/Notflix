package ui.screens.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.vickikbt.shared.domain.utils.Constants.KEY_IMAGE_QUALITY
import com.vickikbt.shared.domain.utils.Constants.KEY_LANGUAGE
import com.vickikbt.shared.domain.utils.Constants.KEY_THEME
import koin
import ui.components.appBars.AppBar
import ui.components.preferences.DialogPreferenceSelection
import ui.components.preferences.PreferencesGroup
import ui.components.preferences.TextPreference
import ui.navigation.NavController

object SettingsScreen : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val title by remember { mutableStateOf("Settings") }
            val icon = painterResource("ic_settings.svg")

            return remember {
                TabOptions(
                    index = 2u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        SettingsComposableScreen()
    }

}

@Composable
fun SettingsComposableScreen(
    navController: NavController? = null,
    viewModel: SettingsScreenModel = koin.get()
) {
    val currentTheme = viewModel.settingsUiState.collectAsState().value.selectedTheme
    val currentLanguage = viewModel.settingsUiState.collectAsState().value.selectedLanguage
    val currentImageQuality =
        viewModel.settingsUiState.collectAsState().value.selectedImageQuality

    val showThemeDialog = remember { mutableStateOf(false) }
    val showLanguageDialog = remember { mutableStateOf(false) }
    val showImageQualityDialog = remember { mutableStateOf(false) }

    val themeLabel = when (currentTheme) {
        0 -> "Light Theme"
        1 -> "Dark Theme"
        else -> "System Default"
    }
    val languageLabel = when (currentLanguage) {
        0 -> "English"
        1 -> "Spanish"
        2 -> "French"
        3 -> "German"
        else -> "English"
    }
    val imageQualityLabel = when (currentImageQuality) {
        0 -> "High Quality"
        1 -> "Medium Quality"
        2 -> "Low Quality"
        else -> "High Quality"
    }

    Scaffold(topBar = { AppBar("Settings") }) {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.surface) {
            Row(modifier = Modifier.fillMaxSize()) {
                Spacer(modifier = Modifier.weight(1f))

                Column(modifier = Modifier.weight(3f)) {
                    Spacer(modifier = Modifier.height(8.dp))

                    PreferencesGroup(title = "Personalization") {
                        TextPreference(
                            icon = painterResource("ic_theme.svg"),
                            title = "Change Theme",
                            subTitle = themeLabel,
                            onClick = { showThemeDialog.value = !showThemeDialog.value }
                        )

                        if (showThemeDialog.value) ChangeTheme(
                            viewModel = viewModel,
                            showDialog = showThemeDialog,
                            currentValue = themeLabel
                        )

                        TextPreference(
                            icon = painterResource("ic_language.svg"),
                            title = "Change Language",
                            subTitle = languageLabel,
                            onClick = { showLanguageDialog.value = !showLanguageDialog.value }
                        )

                        if (showLanguageDialog.value) ChangeLanguage(
                            viewModel = viewModel,
                            showDialog = showLanguageDialog,
                            currentValue = languageLabel
                        )

                        TextPreference(
                            icon = painterResource("ic_image_quality.svg"),
                            title = "Change Image Quality",
                            subTitle = imageQualityLabel,
                            onClick = {
                                showImageQualityDialog.value = !showImageQualityDialog.value
                            }
                        )

                        if (showImageQualityDialog.value) ChangeImageQuality(
                            viewModel = viewModel,
                            showDialog = showImageQualityDialog,
                            currentValue = imageQualityLabel
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    PreferencesGroup(
                        title = "Extras",
                        isLast = true
                    ) {
                        TextPreference(
                            icon = painterResource("ic_bug.svg"),
                            title = "Report Bug",
                            subTitle = "Report bug or request feature",
                            onClick = { reportBug() }
                        )

                        TextPreference(
                            modifier = Modifier.clickable { },
                            icon = painterResource("ic_code.svg"),
                            title = "Source Code",
                            subTitle = "View app source code",
                            onClick = { openSourceCode() }
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun ChangeTheme(
    viewModel: SettingsScreenModel,
    showDialog: MutableState<Boolean>,
    currentValue: String
) {
    DialogPreferenceSelection(
        showDialog = showDialog.value,
        title = "Change Theme",
        currentValue = currentValue,
        labels = listOf("Light Theme", "Dark Theme", "System Default"),
        onNegativeClick = { showDialog.value = false }
    ) { theme ->
        viewModel.savePreferenceSelection(key = KEY_THEME, selection = theme)
    }
}

@Composable
private fun ChangeLanguage(
    viewModel: SettingsScreenModel,
    showDialog: MutableState<Boolean>,
    currentValue: String
) {
    DialogPreferenceSelection(
        showDialog = showDialog.value,
        title = "Change Language",
        currentValue = currentValue,
        labels = listOf("English", "Spanish", "French", "German"),
        onNegativeClick = { showDialog.value = false }
    ) { language ->
        viewModel.savePreferenceSelection(
            key = KEY_LANGUAGE,
            selection = language
        )
    }
}

@Composable
private fun ChangeImageQuality(
    viewModel: SettingsScreenModel,
    showDialog: MutableState<Boolean>,
    currentValue: String
) {
    DialogPreferenceSelection(
        showDialog = showDialog.value,
        title = "Change Image Quality",
        currentValue = currentValue,
        labels = listOf<String>("High Quality", "Medium Quality", "Low Quality"),
        onNegativeClick = { showDialog.value = false }
    ) { imageQuality ->
        viewModel.savePreferenceSelection(
            key = KEY_IMAGE_QUALITY,
            selection = imageQuality
        )
    }
}

private fun reportBug() {
    /*val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
        type = "text/plain"
        data = Uri.parse("mailto:")
        putExtra(Intent.EXTRA_EMAIL, arrayOf(Constants.BUG_REPORT_EMAIL))
        putExtra(Intent.EXTRA_SUBJECT, Constants.BUG_REPORT_SUBJECT)
    }

    context.startActivity(emailIntent)*/
}

private fun openSourceCode() {
    /*val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(Constants.SOURCE_CODE_URL)
    context.startActivity(intent)*/
}

package com.vickikbt.notflix.ui.screens.settings

import com.vickikbt.shared.domain.repositories.SettingsRepository
import com.vickikbt.shared.utils.SettingsUiState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlin.test.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class SettingsViewModelTest {

    // subject under test
    private lateinit var viewModel: SettingsViewModel

    // test helpers
    private val settingsRepository = mockk<SettingsRepository>()

    @Before
    fun setup() {
        viewModel = SettingsViewModel(settingsRepository = settingsRepository)
    }

    @After
    fun teardown() {
        unmockkAll()
    }

    @Test
    fun `savePreferenceSelection is invoked only once on success`() = runTest {
        viewModel.savePreferenceSelection(key = "key", selection = 1)

        coVerify(exactly = 1) {
            settingsRepository.savePreferenceSelection(key = "key", selection = 1)
        }
    }

    @Test
    fun `getThemePreference returns data on success`() = runTest {
        coEvery { settingsRepository.getThemePreference() } returns flowOf(1)

        assertEquals(
            expected = SettingsUiState(isLoading = true),
            actual = viewModel.settingsUiState.value
        )

        viewModel.getThemePreference()

        assertEquals(
            expected = SettingsUiState(isLoading = false, selectedTheme = 1),
            actual = viewModel.settingsUiState.value
        )
    }

    @Test
    fun `getThemePreference returns default value on error`() = runTest {
        coEvery { settingsRepository.getThemePreference() } throws Exception("Error")

        assertEquals(
            expected = SettingsUiState(isLoading = true),
            actual = viewModel.settingsUiState.value
        )

        viewModel.getThemePreference()

        assertEquals(
            expected = SettingsUiState(isLoading = false, selectedTheme = 0, error = "Error"),
            actual = viewModel.settingsUiState.value
        )
    }

    @Test
    fun `getLanguagePreference returns data on success`() = runTest {
        coEvery { settingsRepository.getLanguagePreference() } returns flowOf(1)

        assertEquals(
            expected = SettingsUiState(isLoading = true),
            actual = viewModel.settingsUiState.value
        )

        viewModel.getLanguagePreference()

        assertEquals(
            expected = SettingsUiState(isLoading = false, selectedLanguage = 1),
            actual = viewModel.settingsUiState.value
        )
    }

    @Test
    fun `getLanguagePreference returns default value on error`() = runTest {
        coEvery { settingsRepository.getLanguagePreference() } throws Exception("Error")

        assertEquals(
            expected = SettingsUiState(isLoading = true),
            actual = viewModel.settingsUiState.value
        )

        viewModel.getLanguagePreference()

        assertEquals(
            expected = SettingsUiState(isLoading = false, selectedLanguage = 0, error = "Error"),
            actual = viewModel.settingsUiState.value
        )
    }

    @Test
    fun `getImageQualityPreference returns data on success`() = runTest {
        coEvery { settingsRepository.getImageQualityPreference() } returns flowOf(1)

        assertEquals(
            expected = SettingsUiState(isLoading = true),
            actual = viewModel.settingsUiState.value
        )

        viewModel.getImageQualityPreference()

        assertEquals(
            expected = SettingsUiState(isLoading = false, selectedImageQuality = 1),
            actual = viewModel.settingsUiState.value
        )
    }

    @Test
    fun `getImageQualityPreference returns default value on error`() = runTest {
        coEvery { settingsRepository.getImageQualityPreference() } throws Exception("Error")

        assertEquals(
            expected = SettingsUiState(isLoading = true),
            actual = viewModel.settingsUiState.value
        )

        viewModel.getImageQualityPreference()

        assertEquals(
            expected = SettingsUiState(
                isLoading = false,
                selectedImageQuality = 0,
                error = "Error"
            ),
            actual = viewModel.settingsUiState.value
        )
    }
}

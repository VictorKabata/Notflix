package com.vickikbt.domain.usecases

import com.vickikbt.domain.repositories.ISettingsRepository

class GetSavedThemeUseCase constructor(private val settingsRepository: ISettingsRepository) {

    suspend operator fun invoke()=settingsRepository.getSavedTheme()

}
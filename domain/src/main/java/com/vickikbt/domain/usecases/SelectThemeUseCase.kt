package com.vickikbt.domain.usecases

import com.vickikbt.domain.repositories.ISettingsRepository

class SelectThemeUseCase constructor(private val settingsRepository: ISettingsRepository) {

    suspend operator fun invoke(selectedTheme:Int)=settingsRepository.setTheme(selectedTheme)

}
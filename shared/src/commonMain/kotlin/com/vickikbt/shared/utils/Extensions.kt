package com.vickikbt.shared.domain.utils

import com.vickikbt.shared.presentation.presenters.SharedSettingsPresenter
import org.koin.core.component.KoinComponent
import org.koin.core.component.get


object DomainExtensions : KoinComponent {
    private val settingsRepository: SharedSettingsPresenter = get()

    fun loadImage(link: String): String {

        val quality = when (settingsRepository.selectedImageQuality.value) {
            0 -> "original"
            else -> "w500"
        }

        return "https://image.tmdb.org/t/p/$quality/$link"
    }
}

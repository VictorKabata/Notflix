package com.vickikbt.notflix.di

import com.vickikbt.notflix.ui.screens.home.HomeViewModel
import com.vickikbt.notflix.ui.screens.settings.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { HomeViewModel(get(), get(), get(), get()) }
    viewModel { SettingsViewModel(get()) }
}
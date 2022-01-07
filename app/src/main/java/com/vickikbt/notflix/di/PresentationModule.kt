package com.vickikbt.notflix.di

import com.vickikbt.notflix.ui.screens.home.HomeViewModel
import com.vickikbt.notflix.ui.screens.settings.SettingsViewModel
import com.vickikbt.notflix.util.LocaleUtilCompose
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    single { LocaleUtilCompose(androidApplication()) }

    viewModel { HomeViewModel(get()) }
    
    viewModel { SettingsViewModel(get()) }
}
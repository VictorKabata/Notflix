package com.vickikbt.notflix.di

import com.vickikbt.notflix.ui.screens.details.DetailsViewModel
import com.vickikbt.notflix.ui.screens.favorites.FavoritesViewModel
import com.vickikbt.notflix.ui.screens.home.HomeViewModel
import com.vickikbt.notflix.ui.screens.settings.SettingsViewModel
import com.vickikbt.notflix.util.LocaleManager
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    single { LocaleManager() }

    viewModel { HomeViewModel(get()) }

    viewModel { SettingsViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
    viewModel { FavoritesViewModel(get()) }
}

package com.vickikbt.notflix.di

import com.vickikbt.notflix.ui.activity.MainViewModel
import com.vickikbt.notflix.ui.screens.details.DetailsViewModel
import com.vickikbt.notflix.ui.screens.favorites.FavouritesViewModel
import com.vickikbt.notflix.ui.screens.home.HomeViewModel
import com.vickikbt.notflix.ui.screens.settings.SettingsViewModel
import com.vickikbt.notflix.util.LocaleManager
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val presentationModule = module {

    singleOf(::LocaleManager)

    viewModelOf(::MainViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::DetailsViewModel)
    viewModelOf(::FavouritesViewModel)
    viewModelOf(::SettingsViewModel)
}

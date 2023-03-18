package com.vickikbt.notflix.di

import com.vickikbt.notflix.ui.activity.MainViewModel
import com.vickikbt.notflix.util.LocaleManager
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    single { LocaleManager() }

    viewModel { MainViewModel(get()) }
}

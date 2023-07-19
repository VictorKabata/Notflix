package com.vickikbt.notflix.di

import com.vickikbt.notflix.ui.activity.MainViewModel
import com.vickikbt.notflix.util.LocaleManager
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val presentationModule = module {

    singleOf(::LocaleManager)

    viewModelOf(::MainViewModel)
}

package com.company.favorites.di

import com.company.favorites.ui.fragment.FavoritesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val favoritesModule = module {
    viewModel { FavoritesViewModel(get()) }
}

val loadFavoritesModule by lazy {
    val modules = listOf(favoritesModule)
    loadKoinModules(modules)
}
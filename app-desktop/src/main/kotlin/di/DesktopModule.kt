package di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import ui.screens.details.DetailsViewModel
import ui.screens.favourites.FavouritesViewModel
import ui.screens.home.HomeViewModel
import ui.screens.settings.SettingsViewModel

val desktopModule = module {

    factoryOf(::HomeViewModel)
    factoryOf(::DetailsViewModel)
    factoryOf(::FavouritesViewModel)
    factoryOf(::SettingsViewModel)

}

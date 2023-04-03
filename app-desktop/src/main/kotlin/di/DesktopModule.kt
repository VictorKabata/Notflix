package di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ui.screens.details.DetailsScreenModel
import ui.screens.favourites.FavouritesScreenModel
import ui.screens.home.HomeScreenModel
import ui.screens.settings.SettingsScreenModel

val desktopModule = module {

    singleOf(::HomeScreenModel)
    singleOf(::DetailsScreenModel)
    singleOf(::FavouritesScreenModel)
    singleOf(::SettingsScreenModel)
}

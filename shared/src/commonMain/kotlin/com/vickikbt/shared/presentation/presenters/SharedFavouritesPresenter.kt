package com.vickikbt.shared.presentation.presenters

import com.rickclephas.kmp.nativecoroutines.NativeCoroutineScope
import com.vickikbt.shared.domain.models.MovieDetails
import com.vickikbt.shared.domain.repositories.FavoritesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class SharedFavouritesPresenter constructor(private val favouritesRepository: FavoritesRepository) :
    KoinComponent {

    @NativeCoroutineScope
    private val viewModelScope = CoroutineScope(Dispatchers.Default)

    private val _favouriteMovies = MutableStateFlow<List<MovieDetails>?>(emptyList())
    val favouriteMovies get() = _favouriteMovies.asStateFlow()

    init {
        getFavoriteMovies()
    }

    private fun getFavoriteMovies() = viewModelScope.launch {
        favouritesRepository.getFavouriteMovies().collectLatest { movies ->
            _favouriteMovies.value = movies
        }
    }
}

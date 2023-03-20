package com.vickikbt.notflix.ui.screens.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickikbt.shared.domain.models.MovieDetails
import com.vickikbt.shared.domain.repositories.FavoritesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FavouritesViewModel constructor(private val favouritesRepository: FavoritesRepository) :
    ViewModel() {

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

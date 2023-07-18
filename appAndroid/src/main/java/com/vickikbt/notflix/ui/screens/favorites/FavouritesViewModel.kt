package com.vickikbt.notflix.ui.screens.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickikbt.shared.domain.repositories.FavoritesRepository
import com.vickikbt.shared.utils.FavouritesUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavouritesViewModel constructor(private val favouritesRepository: FavoritesRepository) :
    ViewModel() {

    private val _favouritesUiState = MutableStateFlow(FavouritesUiState(isLoading = true))
    val favouritesUiState = _favouritesUiState.asStateFlow()

    init {
        getFavoriteMovies()
    }

    private fun getFavoriteMovies() = viewModelScope.launch {
        favouritesRepository.getFavouriteMovies().collect { movies ->
            _favouritesUiState.update { it.copy(favouriteMovies = movies) }
        }
    }
}

package com.vickbt.composeApp.ui.screens.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickbt.composeApp.domain.repositories.FavoritesRepository
import com.vickbt.composeApp.utils.FavouritesUiState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {

    private val _favoriteMoviesState = MutableStateFlow(FavouritesUiState())
    val favoriteMoviesState = _favoriteMoviesState.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _favoriteMoviesState.update { it.copy(isLoading = false, error = exception.message) }
    }

    init {
        getFavoriteMovie()
    }

    fun getFavoriteMovie() = viewModelScope.launch(coroutineExceptionHandler) {
        favoritesRepository.getFavouriteMovies().onSuccess { data ->
            data.collectLatest { favoriteMoviesResult ->
                _favoriteMoviesState.update {
                    it.copy(favoriteMovies = favoriteMoviesResult, isLoading = false)
                }
            }
        }.onFailure { error ->
            _favoriteMoviesState.update { it.copy(error = error.message, isLoading = false) }
        }
    }
}

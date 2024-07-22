package com.vickbt.shared.ui.screens.favorites

import androidx.lifecycle.ViewModel
import com.vickbt.shared.domain.repositories.FavoritesRepository
import com.vickbt.shared.utils.FavouritesUiState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {

    private val _favoriteMoviesState = MutableStateFlow(FavouritesUiState())
    val favoriteMoviesState = _favoriteMoviesState.asStateFlow()

    private val viewModelScope = CoroutineScope(Dispatchers.Default)

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _favoriteMoviesState.update { it.copy(isLoading = false, error = exception.message) }
    }

    init {
        getFavoriteMovie()
    }

    fun getFavoriteMovie() = viewModelScope.launch(coroutineExceptionHandler) {
        favoritesRepository.getFavouriteMovies().collect { favoriteMoviesResult ->
            _favoriteMoviesState.update { it.copy(favoriteMovies = favoriteMoviesResult) }
        }
    }
}

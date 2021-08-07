package com.company.favorites.ui.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickikbt.domain.models.Movie
import com.vickikbt.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FavoritesViewModel constructor(private val favoritesRepository: FavoritesRepository) :
    ViewModel() {

    private val _favoriteMovies = MutableStateFlow<FavoritesUiState>(FavoritesUiState.Loading)
    val favoriteMovies: StateFlow<FavoritesUiState> = _favoriteMovies

    init {
        getFavoriteMovies()
    }

    private fun getFavoriteMovies() = viewModelScope.launch {
        _favoriteMovies.value = FavoritesUiState.Loading

        try {
            val response = favoritesRepository.getFavoriteMovies()
            response.collect {
                _favoriteMovies.value = FavoritesUiState.Success(it)
            }
        } catch (e: Exception) {
            _favoriteMovies.value = FavoritesUiState.Error("${e.message}")
        }
    }

    sealed class FavoritesUiState {
        object Loading : FavoritesUiState()
        data class Success(val movies: List<Movie>) : FavoritesUiState()
        data class Error(val error: String) : FavoritesUiState()
    }


}
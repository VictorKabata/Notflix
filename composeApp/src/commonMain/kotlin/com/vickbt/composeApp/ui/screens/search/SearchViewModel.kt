package com.vickbt.composeApp.ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickbt.composeApp.domain.repositories.SearchRepository
import com.vickbt.composeApp.utils.SearchUiState
import com.vickbt.composeApp.utils.isLoading
import com.vickbt.composeApp.utils.onFailure
import com.vickbt.composeApp.utils.onSuccess
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(private val searchRepository: SearchRepository) : ViewModel() {

    private val _searchUiState = MutableStateFlow(SearchUiState(isLoading = false))
    val searchUiState = _searchUiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _searchUiState.update { it.copy(isLoading = false, error = exception.message) }
    }

    init {
        getGenres()
    }

    fun searchMovie(movieName: String) = viewModelScope.launch(coroutineExceptionHandler) {
        searchRepository.searchMovie(movieName = movieName).collectLatest { moviesResult ->
            moviesResult.isLoading { isLoading ->
                _searchUiState.update { it.copy(isLoading = isLoading) }
            }.onSuccess { movies ->
                _searchUiState.update { it.copy(movieResults = movies) }
            }.onFailure { error ->
                _searchUiState.update { it.copy(error = error.message) }
            }
        }
    }

    fun updateSearchQuery(searchQuery: String) {
        _searchQuery.value = searchQuery
    }

    private fun getGenres() = viewModelScope.launch {
    }
}

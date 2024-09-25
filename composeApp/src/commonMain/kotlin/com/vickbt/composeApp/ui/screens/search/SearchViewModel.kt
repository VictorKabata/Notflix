package com.vickbt.composeApp.ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.vickbt.composeApp.domain.repositories.SearchRepository
import com.vickbt.composeApp.utils.SearchUiState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(private val searchRepository: SearchRepository) : ViewModel() {

    private val _searchUiState = MutableStateFlow(SearchUiState(isLoading = false))
    val searchUiState = _searchUiState.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _searchUiState.update { it.copy(isLoading = false, error = exception.message) }
    }

    fun searchMovie(movieName: String) = viewModelScope.launch(coroutineExceptionHandler) {
        _searchUiState.update { it.copy(isLoading = true) }

        searchRepository.searchMovie(movieName = movieName).onSuccess { data ->
            _searchUiState.update {
                it.copy(
                    movieResults = data.cachedIn(viewModelScope),
                    isLoading = false
                )
            }
        }.onFailure { error ->
            _searchUiState.update { it.copy(error = error.message, isLoading = false) }
        }
    }
}

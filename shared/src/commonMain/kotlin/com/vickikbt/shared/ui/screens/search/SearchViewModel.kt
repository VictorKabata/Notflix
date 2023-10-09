package com.vickikbt.shared.ui.screens.search

import com.vickikbt.shared.domain.repositories.MoviesRepository
import com.vickikbt.shared.utils.SearchUiState
import com.vickikbt.shared.utils.isLoading
import com.vickikbt.shared.utils.onFailure
import com.vickikbt.shared.utils.onSuccess
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class SearchViewModel constructor(private val moviesRepository: MoviesRepository) : KoinComponent {

    private val _searchUiState = MutableStateFlow(SearchUiState(isLoading = true))
    val searchUiState = _searchUiState.asStateFlow()

    private val viewModelScope = CoroutineScope(Dispatchers.IO)

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _searchUiState.update { it.copy(isLoading = false, error = exception.message) }
    }

    init {
        searchMovie(movieName = "Justice League") // ToDo: Remove and move to UI
    }

    fun searchMovie(movieName: String) = viewModelScope.launch(coroutineExceptionHandler) {
        moviesRepository.searchMovie(movieName = movieName).collectLatest { moviesResult ->
            moviesResult.isLoading { isLoading ->
                _searchUiState.update { it.copy(isLoading = isLoading) }
            }.onSuccess { movies ->
                _searchUiState.update { it.copy(movieResults = movies) }
            }.onFailure { error ->
                _searchUiState.update { it.copy(error = error.message) }
            }
        }
    }
}

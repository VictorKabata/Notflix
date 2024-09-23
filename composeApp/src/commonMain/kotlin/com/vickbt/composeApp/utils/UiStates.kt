package com.vickbt.composeApp.utils

import app.cash.paging.PagingData
import com.vickbt.composeApp.domain.models.Actor
import com.vickbt.composeApp.domain.models.Movie
import com.vickbt.composeApp.domain.models.MovieDetails

data class MainUiState(
    val selectedTheme: Int? = 0
)

data class HomeUiState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val nowPlayingMovies: List<Movie>? = emptyList(),
    val trendingMovies: PagingData<Movie>? = null,
    val popularMovies: List<Movie>? = emptyList(),
    val upcomingMovies: List<Movie>? = emptyList(),
)

data class DetailsUiState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val movieDetails: MovieDetails? = null,
    val movieCast: List<Actor>? = emptyList(),
    val similarMovies: List<Movie>? = emptyList(),
    val isFavorite: Boolean? = false
)

data class SearchUiState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val movieResults: List<Movie>? = emptyList()
)

data class FavouritesUiState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val favoriteMovies: List<MovieDetails>? = emptyList()
)

data class SettingsUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val selectedTheme: Int = 0,
    val selectedImageQuality: Int = 0
)

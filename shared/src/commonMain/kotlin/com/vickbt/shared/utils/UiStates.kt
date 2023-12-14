package com.vickbt.shared.utils

import com.vickbt.shared.domain.models.Actor
import com.vickbt.shared.domain.models.Movie
import com.vickbt.shared.domain.models.MovieDetails

data class MainUiState(
    val selectedTheme: Int? = 0
)

data class HomeUiState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val nowPlayingMovies: List<Movie>? = emptyList(),
    val trendingMovies: List<Movie>? = emptyList(),
    val popularMovies: List<Movie>? = emptyList(),
    val upcomingMovies: List<Movie>? = emptyList(),
)

data class DetailsUiState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val movieDetails: MovieDetails? = null,
    val movieCast: List<Actor>? = emptyList(),
    val similarMovies: List<Movie>? = emptyList()
)

data class SearchUiState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val movieResults: List<Movie>? = emptyList()
)

data class FavouritesUiState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val favouriteMovies: List<MovieDetails>? = emptyList()
)

data class SettingsUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val selectedTheme: Int = 0,
    val selectedImageQuality: Int = 0
)

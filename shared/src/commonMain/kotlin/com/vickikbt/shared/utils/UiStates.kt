package com.vickikbt.shared.utils

import com.vickikbt.shared.domain.models.Actor
import com.vickikbt.shared.domain.models.Movie
import com.vickikbt.shared.domain.models.MovieDetails

data class HomeUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val nowPlayingMovies: List<Movie>? = emptyList(),
    val trendingMovies: List<Movie>? = emptyList(),
    val popularMovies: List<Movie>? = emptyList(),
    val upcomingMovies: List<Movie>? = emptyList(),
)

data class DetailsUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val movieDetails: MovieDetails? = null,
    val movieCast: List<Actor>? = emptyList(),
    val similarMovies: List<Movie>? = emptyList()
)

data class FavouritesUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val favouriteMovies: List<MovieDetails>? = emptyList()
)

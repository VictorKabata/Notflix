package com.vickbt.shared.domain.models

class Genre(
    val id: String,
    val name: String,
    val shows: List<Movie> = listOf(),
)

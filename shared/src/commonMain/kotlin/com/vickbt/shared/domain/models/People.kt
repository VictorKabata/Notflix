package com.vickbt.shared.domain.models

class People(
    val id: String,
    val name: String,
    val image: String? = null,
    val filmography: List<Movie> = listOf(),
)

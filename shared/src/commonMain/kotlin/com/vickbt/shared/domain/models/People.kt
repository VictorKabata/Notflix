package com.vickbt.shared.domain.models

import com.vickbt.shared.domain.models.Movie
import kotlinx.serialization.Serializable

class People(
    val id: String,
    val name: String,
    val image: String? = null,
    val filmography: List<Movie> = listOf(),
)

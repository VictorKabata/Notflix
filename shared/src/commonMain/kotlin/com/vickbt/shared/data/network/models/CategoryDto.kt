package io.victorkabata.models

import kotlinx.serialization.Serializable

@Serializable
class CategoryDto(
    val name: String,
    val list: List<MovieDto>,
)

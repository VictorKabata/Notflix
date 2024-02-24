package io.victorkabata.models

import kotlinx.serialization.Serializable

@Serializable
class PeopleDto(
    val id: String,
    val name: String,
    val image: String? = null,
    val filmography: List<MovieDto> = listOf(),
)

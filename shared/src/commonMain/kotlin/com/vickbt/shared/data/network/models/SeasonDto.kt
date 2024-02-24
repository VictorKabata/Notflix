package io.victorkabata.models

import kotlinx.serialization.Serializable

@Serializable
class SeasonDto(
    val id: String,
    val number: Int,
    val title: String = "",
    val poster: String = "",
    var tvShow: MovieDto? = null,
    var episodes: List<EpisodeDto> = listOf(),
)

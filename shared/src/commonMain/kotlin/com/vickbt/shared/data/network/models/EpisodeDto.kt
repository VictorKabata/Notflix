package io.victorkabata.models

import kotlinx.serialization.Serializable

@Serializable
class EpisodeDto(
    val id: String,
    val number: Int,
    val title: String = "",
    val released: String? = null,
    val poster: String? = null,
    var tvShow: MovieDto? = null,
    var season: SeasonDto? = null,
)

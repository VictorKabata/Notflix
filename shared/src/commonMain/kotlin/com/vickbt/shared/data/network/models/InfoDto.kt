package io.victorkabata.models

import kotlinx.serialization.Serializable

@Serializable
data class InfoDto(
    val rating: Double? = null,
    val quality: String? = null,
    val released: String? = null,
    val season: Int? = null,
    val episode: Int? = null,
    val lastEpisode: LastEpisodeDto? = null
)

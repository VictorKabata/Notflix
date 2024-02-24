package io.victorkabata.models

import kotlinx.serialization.Serializable

@Serializable
data class LastEpisodeDto(
    val season: Int,
    val episode: Int
)

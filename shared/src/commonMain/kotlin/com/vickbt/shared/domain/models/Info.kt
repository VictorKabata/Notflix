package com.vickbt.shared.domain.models

data class Info(
    val rating: Double? = null,
    val quality: String? = null,
    val released: String? = null,
    val season: Int? = null,
    val episode: Int? = null,
    val lastEpisode: LastEpisode? = null
)

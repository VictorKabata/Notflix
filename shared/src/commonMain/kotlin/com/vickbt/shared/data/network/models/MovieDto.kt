package io.victorkabata.models

import kotlinx.serialization.Serializable

@Serializable
class MovieDto(
    val id: String,
    val title: String,
    val overview: String = "",
    val released: String? = null,
    val runtime: Int? = null,
    val youtubeTrailerId: String? = null,
    val quality: String? = null,
    val rating: Double? = null,
    val poster: String? = null,
    val banner: String? = null,
    val seasons: List<SeasonDto> = listOf(),
    val genres: List<GenreDto> = listOf(),
    val directors: List<PeopleDto> = listOf(),
    val cast: List<PeopleDto> = listOf(),
    val recommendations: List<MovieDto> = listOf(),
    val category: String
)

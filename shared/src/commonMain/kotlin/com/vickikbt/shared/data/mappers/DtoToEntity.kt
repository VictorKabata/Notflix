package com.vickikbt.shared.data.mappers

import com.vickikbt.shared.data.cache.sqldelight.MovieEntity
import com.vickikbt.shared.data.network.models.MovieDto

fun MovieDto.toEntity(category: String? = null): MovieEntity {
    return MovieEntity(
        adult = this.adult,
        backdropPath = this.backdropPath,
        id = this.id,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount?.toLong(),
        category = category ?: "",
        mediaType = this.mediaType
    )
}

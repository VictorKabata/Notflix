package com.vickbt.shared.data.mappers

import com.vickbt.shared.data.cache.room.entities.FavoriteMovieEntity
import com.vickbt.shared.domain.models.MovieDetails

fun FavoriteMovieEntity.toDomain(): MovieDetails {
    return MovieDetails(
        adult = null,
        backdropPath = this.backdropPath,
        homepage = null,
        id = this.id,
        imdbId = this.imdbId,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        runtime = this.runTime,
        status = this.status,
        tagline = this.tagLine,
        title = this.title,
        video = null,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )
}

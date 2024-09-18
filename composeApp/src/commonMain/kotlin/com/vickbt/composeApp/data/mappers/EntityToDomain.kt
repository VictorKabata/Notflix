package com.vickbt.composeApp.data.mappers

import com.vickbt.composeApp.data.cache.room.entities.FavoriteMovieEntity
import com.vickbt.composeApp.domain.models.MovieDetails

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

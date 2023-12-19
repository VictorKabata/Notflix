package com.vickbt.shared.data.mappers

import com.vickbt.shared.FavoriteMovieEntity
import com.vickbt.shared.domain.models.MovieDetails

fun FavoriteMovieEntity.toDomain(): MovieDetails {
    return MovieDetails(
        adult = null,
        backdropPath = this.backdropPath,
        homepage = null,
        id = this.id.toInt(),
        imdbId = this.imdbId,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        runtime = this.runTime?.toInt(),
        status = this.status,
        tagline = this.tagLine,
        title = this.title,
        video = null,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount?.toInt()
    )
}

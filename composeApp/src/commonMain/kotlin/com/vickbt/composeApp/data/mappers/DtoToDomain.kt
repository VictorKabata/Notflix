package com.vickbt.composeApp.data.mappers

import com.vickbt.composeApp.data.network.models.ActorDto
import com.vickbt.composeApp.data.network.models.CastDto
import com.vickbt.composeApp.data.network.models.ErrorResponseDto
import com.vickbt.composeApp.data.network.models.MovieDetailsDto
import com.vickbt.composeApp.data.network.models.MovieDto
import com.vickbt.composeApp.data.network.models.TvShowDto
import com.vickbt.composeApp.data.network.models.VideoDto
import com.vickbt.composeApp.domain.models.Actor
import com.vickbt.composeApp.domain.models.Cast
import com.vickbt.composeApp.domain.models.ErrorResponse
import com.vickbt.composeApp.domain.models.Movie
import com.vickbt.composeApp.domain.models.MovieDetails
import com.vickbt.composeApp.domain.models.TvShow
import com.vickbt.composeApp.domain.models.Video

fun MovieDto.toDomain(): Movie {
    return Movie(
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
        voteCount = this.voteCount,
        mediaType = this.mediaType
    )
}

fun TvShowDto.toDomain(): TvShow {
    return TvShow(
        adult = this.adult,
        backdropPath = this.backdropPath,
        id = this.id,
        name = this.name,
        overview = this.overview,
        posterPath = this.posterPath,
        mediaType = this.mediaType,
        genreIds = this.genreIds,
        popularity = this.popularity,
        firstAirDate = this.firstAirDate,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount,
        originCountry = this.originCountry
    )
}

fun MovieDetailsDto.toDomain(): MovieDetails {
    return MovieDetails(
        adult = this.adult,
        backdropPath = this.backdropPath,
        homepage = this.homepage,
        id = this.id,
        imdbId = this.imdbId,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        runtime = this.runtime,
        status = this.status,
        tagline = this.tagline,
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )
}

fun ActorDto.toDomain(): Actor {
    return Actor(
        castId = this.castId,
        character = this.character,
        creditId = this.creditId,
        id = this.id,
        name = this.name,
        originalName = this.originalName,
        profilePath = this.profilePath
    )
}

fun CastDto.toDomain(): Cast {
    return Cast(
        actor = this.actor?.map { it.toDomain() },
        id = this.id
    )
}

fun VideoDto.toDomain(): Video {
    return Video(
        id = this.id,
        iso31661 = this.iso31661,
        iso6391 = this.iso6391,
        key = this.key,
        name = this.name,
        official = this.official,
        publishedAt = this.publishedAt,
        site = this.site,
        size = this.size,
        type = this.type
    )
}

/*fun MovieVideoDto.toDomain(): MovieVideo {
    return MovieVideo(
        id = this.id,
        videos = this.videos?.map { it.toDomain() }
    )
}*/

fun ErrorResponseDto.toDomain(): ErrorResponse {
    return ErrorResponse(
        success = this.success,
        statusCode = this.statusCode,
        statusMessage = this.statusMessage
    )
}

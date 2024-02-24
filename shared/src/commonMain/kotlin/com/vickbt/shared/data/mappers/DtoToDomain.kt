package com.vickbt.shared.data.mappers

import com.vickbt.shared.data.network.models.ErrorResponseDto
import com.vickbt.shared.domain.models.Episode
import com.vickbt.shared.domain.models.ErrorResponse
import com.vickbt.shared.domain.models.Genre
import com.vickbt.shared.domain.models.Movie
import com.vickbt.shared.domain.models.People
import com.vickbt.shared.domain.models.Season
import io.victorkabata.models.EpisodeDto
import io.victorkabata.models.GenreDto
import io.victorkabata.models.MovieDto
import io.victorkabata.models.PeopleDto
import io.victorkabata.models.SeasonDto

fun MovieDto.toDomain(): Movie {
    return Movie(
        id = this.id,
        title = this.title,
        overview = this.overview,
        released = this.released,
        runtime = this.runtime,
        youtubeTrailerId = this.youtubeTrailerId,
        quality = this.quality,
        rating = this.rating,
        poster = this.poster,
        banner = this.banner,
        seasons = this.seasons.map { it.toDomain() },
        genres = this.genres.map { it.toDomain() },
        directors = this.directors.map { it.toDomain() },
        cast = this.cast.map { it.toDomain() },
        recommendations = this.recommendations.map { it.toDomain() },
        category = this.category
    )
}

fun SeasonDto.toDomain(): Season {
    return Season(
        id = this.id,
        number = this.number,
        title = this.title,
        poster = this.poster,
        tvShow = this.tvShow?.toDomain(),
        episodes = this.episodes.map { it.toDomain() }
    )
}

fun EpisodeDto.toDomain(): Episode {
    return Episode(
        id = this.id,
        number = this.number,
        title = this.title,
        released = this.released,
        poster = this.poster,
        tvShow = this.tvShow?.toDomain(),
        season = this.season?.toDomain()
    )
}

fun GenreDto.toDomain(): Genre {
    return Genre(id = this.id, name = this.name, shows = this.shows.map { it.toDomain() })
}

fun PeopleDto.toDomain(): People {
    return People(
        id = this.id,
        name = this.name,
        image = this.image,
        filmography = this.filmography.map { it.toDomain() }
    )
}

fun ErrorResponseDto.toDomain(): ErrorResponse {
    return ErrorResponse(errorCode = this.errorCode, errorMessage = this.errorMessage)
}


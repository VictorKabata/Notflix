package com.vickikbt.shared.data.mappers

import com.vickikbt.shared.data.network.models.*
import com.vickikbt.shared.domain.models.*
import kotlin.test.Test
import kotlin.test.assertEquals

class DtoToDomainTest {

    @Test
    fun `convert movieDto to movie`() {
        val result = MovieDto(
            adult = true,
            backdropPath = "backdropPath",
            genreIds = emptyList(),
            id = 1,
            originalLanguage = "originalLanguage",
            originalTitle = "originalTitle",
            overview = "overview",
            popularity = 1.0,
            posterPath = "posterPath",
            releaseDate = "releaseDate",
            title = "title",
            video = true,
            voteAverage = 1.0,
            voteCount = 1,
            mediaType = "mediaType"
        )

        val expected = Movie(
            adult = true,
            backdropPath = "backdropPath",
            id = 1,
            originalLanguage = "originalLanguage",
            originalTitle = "originalTitle",
            overview = "overview",
            popularity = 1.0,
            posterPath = "posterPath",
            releaseDate = "releaseDate",
            title = "title",
            video = true,
            voteAverage = 1.0,
            voteCount = 1,
            mediaType = "mediaType",
            category = "category",
            isFavorite = null,
            cacheId = 0
        )

        assertEquals(expected = expected, actual = result.toDomain(category = "category"))
    }

    @Test
    fun `convert movieDetailsDto to movieDetails`() {
        val result = MovieDetailsDto(
            adult = true,
            backdropPath = "backdropPath",
            homepage = "homepage",
            id = 1,
            imdbId = "imdbId",
            originalLanguage = "originalLanguage",
            originalTitle = "originalTitle",
            overview = "overview",
            popularity = 1.0,
            posterPath = "posterPath",
            releaseDate = "releaseDate",
            runtime = 1,
            status = "status",
            tagline = "tagline",
            title = "title",
            video = true,
            voteAverage = 1.0,
            voteCount = 1
        )

        val expected = MovieDetails(
            adult = true,
            backdropPath = "backdropPath",
            homepage = "homepage",
            id = 1,
            imdbId = "imdbId",
            originalLanguage = "originalLanguage",
            originalTitle = "originalTitle",
            overview = "overview",
            popularity = 1.0,
            posterPath = "posterPath",
            releaseDate = "releaseDate",
            runtime = 1,
            status = "status",
            tagline = "tagline",
            title = "title",
            video = true,
            voteAverage = 1.0,
            voteCount = 1
        )

        assertEquals(expected = expected, actual = result.toDomain())
    }

    @Test
    fun `convert actorDto to actor`() {
        val result = ActorDto(
            castId = 1,
            character = "character",
            creditId = "creditId",
            id = 1,
            name = "name",
            originalName = "originalName",
            profilePath = "profilePath"
        )

        val expected = Actor(
            castId = 1,
            character = "character",
            creditId = "creditId",
            id = 1,
            name = "name",
            originalName = "originalName",
            profilePath = "profilePath"
        )

        assertEquals(expected = expected, actual = result.toDomain())
    }

    @Test
    fun `convert castDto to cast`() {
        val result = CastDto(actor = emptyList(), id = 1)

        val expected = Cast(actor = emptyList(), id = 1)

        assertEquals(expected = expected, actual = result.toDomain())
    }

    @Test
    fun `convert videoDto to video`() {
        val result = VideoDto(
            id = "1",
            iso31661 = "iso31661",
            iso6391 = "iso6391",
            key = "key",
            name = "name",
            official = true,
            publishedAt = "publishedAt",
            site = "site",
            size = 1,
            type = "type"
        )

        val expected = Video(
            id = "1",
            iso31661 = "iso31661",
            iso6391 = "iso6391",
            key = "key",
            name = "name",
            official = true,
            publishedAt = "publishedAt",
            site = "site",
            size = 1,
            type = "type"
        )

        assertEquals(expected = expected, actual = result.toDomain())
    }

    @Test
    fun `convert movieVideoDto to movieVideo`() {
        val result = MovieVideoDto(id = 1, videos = emptyList())

        val expected = MovieVideo(id = 1, videos = emptyList())

        assertEquals(expected = expected, actual = result.toDomain())
    }
}

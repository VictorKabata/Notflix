package com.vickikbt.shared.data.data_sources

import com.vickikbt.shared.data.cache.sqldelight.daos.MovieDao
import com.vickikbt.shared.data.mappers.toDomain
import com.vickikbt.shared.data.mappers.toEntity
import com.vickikbt.shared.domain.models.Cast
import com.vickikbt.shared.domain.models.Movie
import com.vickikbt.shared.domain.models.MovieDetails
import com.vickikbt.shared.domain.repositories.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class FavoriteMovieRepositoryImpl constructor(private val movieDao: MovieDao) :
    FavoritesRepository {

    override suspend fun isMovieFavorite(movieId: Int): Flow<Boolean?> = flowOf(true)

    override suspend fun saveFavouriteMovie(movieDetail: MovieDetails) {
        movieDao.saveMovieDetails(movieDetailsEntity = movieDetail.toEntity())
    }

    override suspend fun getFavouriteMovies(): Flow<List<MovieDetails>> {
        return movieDao.getFavouriteMovies()
            .map { it.map { movieDetailsEntity -> movieDetailsEntity.toDomain() } }
    }

    override suspend fun saveFavouriteCast(actor: Cast) {
        TODO("Not yet implemented")
    }

    /*override suspend fun saveFavouriteMovie(movieDetail: MovieDetails, actors: List<Actor>) {
        val movieDetailsEntity = MovieDetailsEntity(
            adult = movieDetail.adult,
            backdropPath = movieDetail.backdropPath,
            homepage = movieDetail.homepage,
            id = movieDetail.id,
            imdbId = movieDetail.imdbId!!,
            originalLanguage = movieDetail.originalLanguage,
            originalTitle = movieDetail.originalTitle,
            overview = movieDetail.overview,
            popularity = movieDetail.popularity?.toInt(),
            posterPath = movieDetail.posterPath,
            releaseDate = movieDetail.releaseDate,
            runtime = movieDetail.runtime,
            status = movieDetail.status,
            tagline = movieDetail.tagline,
            title = movieDetail.title,
            video = movieDetail.video,
            voteAverage = movieDetail.voteAverage?.toInt(),
            voteCount = movieDetail.voteCount,
            cast = actors.map { it.toEntity() }
        )


        movieDao.saveMovieDetails(movieDetailsEntity = movieDetailsEntity)
    }*/
}

package com.vickbt.shared.data.datasources

import com.vickbt.shared.domain.repositories.FavoritesRepository

class FavoritesRepositoryImpl : FavoritesRepository {

    /*override suspend fun getFavouriteMovies(): Flow<List<MovieDetails>> {
        return favoriteMovieDao.getAllFavoriteMovies()
            .map { it.map { movieDetail -> movieDetail.toDomain() } }
    }*/
}

package com.vickikbt.shared.data.cache.sqldelight.daos

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.vickikbt.shared.data.cache.sqldelight.AppDatabase
import com.vickikbt.shared.data.cache.sqldelight.CastEntity
import com.vickikbt.shared.data.cache.sqldelight.MovieDetailsEntity
import com.vickikbt.shared.data.cache.sqldelight.MovieEntity
import com.vickikbt.shared.data.cache.sqldelight.adapters.castCustomAdapter
import com.vickikbt.shared.domain.utils.DatabaseDriverFactory
import kotlinx.coroutines.flow.map

class MovieDao constructor(private val databaseDriverFactory: DatabaseDriverFactory) {

    private val appDatabase = AppDatabase(
        driver = databaseDriverFactory.createDriver(),
        CastEntityAdapter = CastEntity.Adapter(castCustomAdapter)
    )
    private val dbQuery = appDatabase.appDatabaseQueries

    /**Cache movies to database*/
    suspend fun saveMovies(movies: List<MovieEntity>) {
        dbQuery.transaction {
            movies.onEach { movieEntity ->
                dbQuery.saveMovies(movieEntity)
            }
        }
    }

    /**Get cached movies from database*/
    suspend fun getMoviesByCategory(category: String) =
        dbQuery.getMoviesByCategory(category = category).asFlow().map { it.executeAsList() }

    /**Cache favourite movie detail*/
    suspend fun saveMovieDetails(movieDetailsEntity: MovieDetailsEntity) {
        dbQuery.transaction {
            dbQuery.saveMovieDetails(movieDetailsEntity)
        }
    }

    /**Get a list of cached favourite movie*/
    suspend fun getFavouriteMovies() =
        dbQuery.getFavouriteMovies().asFlow().map { it.executeAsList() }

    /**Get cached movie details based on ID*/
    suspend fun getMovieDetails(id: Int) =
        dbQuery.getMovieDetails(id = id).asFlow().map { it.executeAsOneOrNull() }

    /**Cache favourite movie cast*/
    suspend fun saveMovieCast(cast: CastEntity) {
        dbQuery.transaction {
            dbQuery.saveMovieCast(cast)
        }
    }

    /**Get a list of favourite movie cast based on ID*/
    suspend fun getMovieCast(id: Int) =
        dbQuery.getMovieCast(id = id).asFlow().map { it.executeAsOneOrNull() }
}

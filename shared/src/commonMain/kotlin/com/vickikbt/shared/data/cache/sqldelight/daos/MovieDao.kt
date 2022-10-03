package com.vickikbt.shared.data.cache.sqldelight.daos

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.vickikbt.shared.data.cache.sqldelight.AppDatabase
import com.vickikbt.shared.data.cache.sqldelight.MovieEntity
import com.vickikbt.shared.domain.utils.DatabaseDriverFactory
import kotlinx.coroutines.flow.map

class MovieDao constructor(private val databaseDriverFactory: DatabaseDriverFactory) {

    private val appDatabase = AppDatabase(databaseDriverFactory.createDriver())
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

}

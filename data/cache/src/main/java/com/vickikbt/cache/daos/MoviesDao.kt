package com.vickikbt.cache.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vickikbt.cache.models.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveMovies(movieEntities: List<MovieEntity>)

    @Query("SELECT * FROM `Movies Table` WHERE category=:category")
    fun getMovies(category: String): Flow<List<MovieEntity>>

    @Query("DELETE FROM `Movies Table` WHERE category=:category")
    suspend fun deleteMovies(category: String)

    @Query("SELECT COUNT(*) FROM `Movies Table` WHERE category=:category")
    suspend fun isCategoryCacheAvailable(category: String): Int

    @Query("SELECT * FROM `Movies Table` WHERE isFavorite=:isFavorite")
    fun getFavoriteMovies(isFavorite: Boolean = true): Flow<List<MovieEntity>>

    @Query("SELECT isFavorite FROM `Movies Table` WHERE id=:movieId")
    fun isMovieFavorite(movieId: Int): Flow<Boolean>

    @Query("UPDATE `Movies Table` SET isFavorite=:isFavorite WHERE id=:movieId")
    suspend fun updateMovieIsFavorite(movieId: Int, isFavorite: Boolean)

}
package com.vickikbt.cache.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vickikbt.cache.models.MovieDetailsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovieDetails(movieDetails: MovieDetailsEntity)

    @Query("SELECT * FROM `Movie Details Table` WHERE id=:movieId")
    fun getMovieDetails(movieId: Int): Flow<MovieDetailsEntity>

    //TODO: setup WorkManager to delete movie details after 30 days
    @Query("DELETE FROM `Movie Details Table`")
    suspend fun deleteAllMovieDetails()

}
package com.vickikbt.data.cache.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vickikbt.data.models.entity.MovieDetailsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovieDetails(movieDetails: MovieDetailsEntity)

    @Query("SELECT * FROM Movie_Details_Table WHERE ID=:movieId")
    fun getPopularShows(movieId: Int): Flow<MovieDetailsEntity>?

}
package com.vickikbt.data.cache.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vickikbt.data.models.entity.MovieDetailsEntity

@Dao
interface MovieDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovieDetails(movieDetails: MovieDetailsEntity)

    //TODO: Change this query to return Flow<MovieDetailsEntity>?
    @Query("SELECT * FROM Movie_Details_Table WHERE ID=:movieId")
    suspend fun getMovieDetails(movieId: Int): MovieDetailsEntity?

    //TODO: setup WorkManager to delete movie details after 30 days
    @Query("DELETE FROM Movie_Details_Table")
    suspend fun deleteAllMovieDetails()

}
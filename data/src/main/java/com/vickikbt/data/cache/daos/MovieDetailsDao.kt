package com.vickikbt.data.cache.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vickikbt.data.models.entity.MovieDetailsEntity
import com.vickikbt.data.models.entity.MovieEntity
import com.vickikbt.domain.models.MovieDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDetailsDao {

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    suspend fun saveMovieDetails(movieDetailsEntity:MovieDetailsEntity)

    @Query("SELECT * FROM `Movie Details Table` WHERE id=:movieId")
    fun getMovieDetails(movieId:Int):Flow<MovieDetailsEntity>


}
package com.vickikbt.data.cache.daos

import androidx.room.Dao
import androidx.room.Query
import com.vickikbt.data.models.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDetailsDao {
    //This interface class is used to JOIN movieId from various tables

    //@Query("SELECT * FROM `Popular Result Table` INNER JOIN `Upcoming Result Table` ON `Popular Result Table.` ")

    //@Query("SELECT * FROM `Popular Result Table`, `Upcoming Result Table` WHERE id=:=id")
    //suspend fun getMovieDetails(id:Int): Flow<MovieEntity>


}
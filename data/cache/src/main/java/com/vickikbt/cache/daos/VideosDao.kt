package com.vickikbt.cache.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vickikbt.cache.models.VideoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VideosDao {

    @Insert
    suspend fun saveMovieVideo(videoEntity: VideoEntity)

    //TODO: Change this query to return Flow<VideoEntity>?
    @Query("SELECT * FROM `Movie Videos Table` WHERE id=:movieId")
    suspend fun getMovieVideo(movieId: Int): Flow<VideoEntity>

    //TODO: setup WorkManager to delete movie videos after 30 days
    @Query("DELETE FROM `Movie Videos Table`")
    suspend fun deleteAllMovieVideos()

}
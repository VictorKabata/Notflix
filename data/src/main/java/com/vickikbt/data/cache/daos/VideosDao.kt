package com.vickikbt.data.cache.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vickikbt.data.models.entity.CastEntity
import com.vickikbt.data.models.entity.VideoEntity

@Dao
interface VideosDao {

    @Insert
    suspend fun saveMovieVideo(videoEntity: VideoEntity)

    //TODO: Change this query to return Flow<VideoEntity>?
    @Query("SELECT * FROM Videos_Table WHERE ID=:movieId")
    suspend fun getMovieVideo(movieId: Int): VideoEntity?

    //TODO: setup WorkManager to delete movie videos after 30 days
    @Query("DELETE FROM Cast_Table")
    suspend fun deleteAllMovieVideos()

}
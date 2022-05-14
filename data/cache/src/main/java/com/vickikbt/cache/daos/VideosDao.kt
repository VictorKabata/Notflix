package com.vickikbt.cache.daos

/*@Dao
interface VideosDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovieVideo(movieVideoEntity: MovieVideoEntity)

    @Query("SELECT * FROM `Movie Videos Table` WHERE id=:movieId")
    fun getMovieVideo(movieId: Int): Flow<MovieVideoEntity>

    // TODO: setup WorkManager to delete movie videos after 30 days
    @Query("DELETE FROM `Movie Videos Table`")
    suspend fun deleteAllMovieVideos()

    @Query("SELECT COUNT(*) FROM `Movie Videos Table` WHERE id=:movieId")
    suspend fun isMovieVideoCacheAvailable(movieId: Int): Int
}*/

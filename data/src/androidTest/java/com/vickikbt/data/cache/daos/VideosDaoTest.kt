package com.vickikbt.data.cache.daos

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.vickikbt.data.cache.AppDatabase
import com.vickikbt.data.models.entity.VideoEntity
import com.vickikbt.data.models.entity.VideoItemEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class VideosDaoTest {

    private lateinit var appDatabase: AppDatabase
    private lateinit var videosDao: VideosDao

    private val videoItemEntity =
        VideoItemEntity("1", "iso", "iso", "key", "name", "site", 1, "type")
    private val videoEntity = VideoEntity(1, listOf(videoItemEntity))

    @Before
    fun setUp() {
        appDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        videosDao = appDatabase.videsoDao()
    }

    @After
    fun tearDown() {
        appDatabase.close()
    }

    @Test
    fun saveMovieVideoTest() = runBlocking {
        videosDao.saveMovieVideo(videoEntity)

        val result = videosDao.getMovieVideo(1)

        assertThat(result).isEqualTo(videoEntity)
    }

    @Test
    fun getMovieVideoTest() = runBlocking {
        videosDao.saveMovieVideo(videoEntity)

        val result = videosDao.getMovieVideo(1)

        assertThat(result).isEqualTo(videoEntity)
    }

    @Test
    fun deleteAllMovieVideosTest() = runBlocking {
        videosDao.saveMovieVideo(videoEntity)
        videosDao.deleteAllMovieVideos()

        val result = videosDao.getMovieVideo(1)

        assertThat(result).isNull()
    }

}
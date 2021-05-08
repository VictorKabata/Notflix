package com.vickikbt.data.cache.daos

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.vickikbt.data.cache.AppDatabase
import com.vickikbt.data.models.entity.MovieEntity
import com.vickikbt.data.models.entity.UpcomingDatesEntity
import com.vickikbt.data.models.entity.UpcomingResultEntity
import com.vickikbt.domain.models.UpcomingDates
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class UpcomingShowsDaoTest {

    private lateinit var appDatabase: AppDatabase
    private lateinit var upcomingShowsDao: UpcomingShowsDao

    private val upcomingDatesEntity=UpcomingDatesEntity("maximum", "minimum")

    private val movieEntity = MovieEntity(
        true,
        "backdrop_url",
        listOf(1, 2),
        1,
        "original_language",
        "original_title",
        "overview",
        1.0,
        "poster_url",
        "release",
        "title",
        false,
        1.0,
        1
    )

    private val upcomingResultEntity=UpcomingResultEntity(upcomingDatesEntity,1, listOf(movieEntity),2,100)

    @Before
    fun setUp() {
        appDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        upcomingShowsDao = appDatabase.upcomingShowsDao()
    }

    @After
    fun tearDown() {
        appDatabase.close()
    }

    @Test
    fun saveUpcomingShowsTest()= runBlocking {
        upcomingShowsDao.saveUpcomingShows(upcomingResultEntity)

        val result=upcomingShowsDao.getUpcomingShows().take(1).toList()[0]

        assertThat(result).isEqualTo(upcomingResultEntity)
    }

    @Test
    fun getUpcomingShowsTest()= runBlocking {
        upcomingShowsDao.saveUpcomingShows(upcomingResultEntity)

        val result=upcomingShowsDao.getUpcomingShows().take(1).toList()[0]

        assertThat(result).isEqualTo(upcomingResultEntity)
    }

    @Test
    fun deleteUpcomingShowsTest()= runBlocking {
        upcomingShowsDao.saveUpcomingShows(upcomingResultEntity)
        upcomingShowsDao.deleteUpcomingShows()

        val result=upcomingShowsDao.getUpcomingShows().take(1).toList()[0]

        assertThat(result).isNull()
    }

    @Test
    fun isPopularCacheAvailableTest()= runBlocking {
        upcomingShowsDao.saveUpcomingShows(upcomingResultEntity)

        val isCacheAvailable=upcomingShowsDao.isUpcomingCacheAvailable()>0

        assertThat(isCacheAvailable).isTrue()
    }

}
package com.vickikbt.data.cache.daos

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.vickikbt.data.cache.AppDatabase
import com.vickikbt.data.models.entity.MovieEntity
import com.vickikbt.data.models.entity.PopularResultEntity
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class PopularShowDaoTest {

    private lateinit var appDatabase: AppDatabase
    private lateinit var popularShowsDao: PopularShowsDao

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
    private val popularResultEntity = PopularResultEntity(1, listOf(movieEntity), 2, 100)

    @Before
    fun setUp() {
        appDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        popularShowsDao = appDatabase.popularShowsDao()
    }

    @After
    fun tearDown() {
        appDatabase.close()
    }

    @Test
    fun savePopularShowsTest() = runBlocking {
        popularShowsDao.savePopularShows(popularResultEntity)

        val result = popularShowsDao.getPopularShows().take(1).toList()[0]

        assertThat(result).isEqualTo(popularResultEntity)
    }

    @Test
    fun getPopularShowsTest() = runBlocking {
        popularShowsDao.savePopularShows(popularResultEntity)

        val result = popularShowsDao.getPopularShows().take(1).toList()[0]

        assertThat(result).isEqualTo(popularResultEntity)
    }

    @Test
    fun deletePopularShowsTest()= runBlocking{
        popularShowsDao.savePopularShows(popularResultEntity)
        popularShowsDao.deletePopularShows()

        val result = popularShowsDao.getPopularShows().take(1).toList()[0]

        assertThat(result).isNull()
    }

    @Test
    fun isPopularCacheAvailableTest()= runBlocking {
        popularShowsDao.savePopularShows(popularResultEntity)

        val isCacheAvailable=popularShowsDao.isPopularCacheAvailable()>0

        assertThat(isCacheAvailable).isTrue()
    }

}
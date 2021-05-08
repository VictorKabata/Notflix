package com.vickikbt.data.cache.daos

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.vickikbt.data.cache.AppDatabase
import com.google.common.truth.Truth.assertThat
import com.vickikbt.data.models.entity.*
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class MovieDetailsDaoTest {

    private lateinit var appDatabase: AppDatabase
    private lateinit var movieDetailsDao: MovieDetailsDao

    private val genreEntity = GenreEntity(1, "name")
    private val productionCompanyEntity = ProductionCompanyEntity(1, "logo_url", "name", "country")
    private val productionCountryEntity = ProductionCountryEntity("iso", "name")
    private val spokenLanguageEntity = SpokenLanguageEntity("english_name", "iso", "name")
    private val movieDetailsEntity = MovieDetailsEntity(
        true,
        "backdrop_url",
        1000,
        listOf(genreEntity),
        "homepage_url",
        1,
        "1",
        "language",
        "title",
        "overview",
        1.0,
        "poster_url",
        listOf(productionCompanyEntity),
        listOf(productionCountryEntity),
        "release_date",
        1500,
        120,
        listOf(spokenLanguageEntity),
        "status",
        "tagline",
        "title",
        false,
        1.0,
        1
    )

    @Before
    fun setUp() {
        appDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        movieDetailsDao = appDatabase.movieDetailsDao()
    }

    @After
    fun tearDown() {
        appDatabase.close()
    }

    @Test
    fun saveMovieDetails()= runBlocking {
        movieDetailsDao.saveMovieDetails(movieDetailsEntity)

        val result=movieDetailsDao.getMovieDetails(1)

        assertThat(result).isEqualTo(movieDetailsEntity)
    }

    @Test
    fun getMovieDetails()= runBlocking{
        movieDetailsDao.saveMovieDetails(movieDetailsEntity)

        val result=movieDetailsDao.getMovieDetails(1)

        assertThat(result).isEqualTo(movieDetailsEntity)
    }

    @Test
    fun deleteMovieDetails()= runBlocking {
        movieDetailsDao.saveMovieDetails(movieDetailsEntity)
        movieDetailsDao.deleteAllMovieDetails()

        val result=movieDetailsDao.getMovieDetails(1)

        assertThat(result).isNull()
    }

}
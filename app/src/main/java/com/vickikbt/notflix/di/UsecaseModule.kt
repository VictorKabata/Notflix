package com.vickikbt.notflix.di

import com.vickikbt.data.cache.AppDatabase
import com.vickikbt.data.datastore.TimeDatastore
import com.vickikbt.data.network.ApiService
import com.vickikbt.data.repository.MovieDetailsRepository
import com.vickikbt.data.repository.PopularMoviesRepository
import com.vickikbt.data.repository.UpcomingMoviesRepository
import com.vickikbt.data.sources.CastDataSource
import com.vickikbt.data.sources.MovieDetailsDataSource
import com.vickikbt.data.sources.PopularMoviesDataSource
import com.vickikbt.data.sources.UpcomingMoviesDataSource
import com.vickikbt.domain.usecases.FetchMovieCastUseCase
import com.vickikbt.domain.usecases.FetchMovieDetailsUseCase
import com.vickikbt.domain.usecases.FetchPopularMoviesUsecase
import com.vickikbt.domain.usecases.FetchUpcomingMoviesUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent


@InstallIn(ActivityRetainedComponent::class)

@Module
object UsecaseModule {

    @Provides
    fun providesPopularMoviesDataSource(
        apiService: ApiService,
        appDatabase: AppDatabase,
        timeDatastore: TimeDatastore
    ): PopularMoviesDataSource {
        return PopularMoviesDataSource(apiService, appDatabase, timeDatastore)
    }

    @Provides
    fun providesPopularMoviesRepository(popularMoviesDataSource: PopularMoviesDataSource): PopularMoviesRepository {
        return PopularMoviesRepository(popularMoviesDataSource)
    }

    @Provides
    fun providesFetchPopularMoviesUsecase(popularMoviesRepository: PopularMoviesRepository): FetchPopularMoviesUsecase {
        return FetchPopularMoviesUsecase(popularMoviesRepository)
    }

    @Provides
    fun providesUpcomingMoviesDataSource(
        apiService: ApiService,
        appDatabase: AppDatabase,
        timeDatastore: TimeDatastore
    ): UpcomingMoviesDataSource {
        return UpcomingMoviesDataSource(apiService, appDatabase, timeDatastore)
    }

    @Provides
    fun providesUpcomingMoviesRepository(upcomingMoviesDataSource: UpcomingMoviesDataSource): UpcomingMoviesRepository {
        return UpcomingMoviesRepository(upcomingMoviesDataSource)
    }

    @Provides
    fun providesFetchUpcomingMoviesUsecase(upcomingMoviesRepository: UpcomingMoviesRepository): FetchUpcomingMoviesUsecase {
        return FetchUpcomingMoviesUsecase(upcomingMoviesRepository)
    }

    @Provides
    fun providesMoviesDetailsDataSource(
        apiService: ApiService,
        appDatabase: AppDatabase
    ): MovieDetailsDataSource {
        return MovieDetailsDataSource(apiService, appDatabase)
    }

    @Provides
    fun providesCastDataSource(
        apiService: ApiService,
        appDatabase: AppDatabase
    ): CastDataSource {
        return CastDataSource(apiService, appDatabase)
    }

    @Provides
    fun providesMoviesDetailsRepository(
        movieDetailsDataSource: MovieDetailsDataSource,
        castDataSource: CastDataSource
    ): MovieDetailsRepository {
        return MovieDetailsRepository(movieDetailsDataSource, castDataSource)
    }

    @Provides
    fun providesGetMoviesDetailsUsecase(movieDetailsRepository: MovieDetailsRepository): FetchMovieDetailsUseCase {
        return FetchMovieDetailsUseCase(movieDetailsRepository)
    }

    @Provides
    fun providesGetMoviesCastUsecase(movieDetailsRepository: MovieDetailsRepository): FetchMovieCastUseCase {
        return FetchMovieCastUseCase(movieDetailsRepository)
    }

}


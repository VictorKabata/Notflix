package com.vickikbt.notflix.di

import com.vickikbt.data.cache.AppDatabase
import com.vickikbt.data.datastore.ThemeDatastore
import com.vickikbt.data.datastore.TimeDatastore
import com.vickikbt.data.network.ApiService
import com.vickikbt.data.repository.MovieDetailsRepository
import com.vickikbt.data.repository.PopularMoviesRepository
import com.vickikbt.data.repository.SettingsRepository
import com.vickikbt.data.repository.UpcomingMoviesRepository
import com.vickikbt.data.sources.*
import com.vickikbt.domain.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent


@InstallIn(ActivityRetainedComponent::class)

@Module
object UsecaseModule {

    //region PopularMoviesUseCase Modules
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
    //endregion

    //region UpcomingMoviesUseCase Modules
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
    //endregion

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
    fun providesVideosDataSource(
        apiService: ApiService,
        appDatabase: AppDatabase
    ): VideoDataSource {
        return VideoDataSource(apiService, appDatabase)
    }

    @Provides
    fun providesSimilarMoviesDataSource(
        apiService: ApiService,
        appDatabase: AppDatabase
    ): SimilarMoviesDataSource {
        return SimilarMoviesDataSource(apiService, appDatabase)
    }

    @Provides
    fun providesMoviesDetailsRepository(
        movieDetailsDataSource: MovieDetailsDataSource,
        castDataSource: CastDataSource,
        videoDataSource: VideoDataSource,
        similarMoviesDataSource: SimilarMoviesDataSource
    ): MovieDetailsRepository {
        return MovieDetailsRepository(
            movieDetailsDataSource,
            castDataSource,
            videoDataSource,
            similarMoviesDataSource
        )
    }

    @Provides
    fun providesGetMoviesDetailsUsecase(movieDetailsRepository: MovieDetailsRepository): FetchMovieDetailsUseCase {
        return FetchMovieDetailsUseCase(movieDetailsRepository)
    }

    @Provides
    fun providesGetMoviesCastUsecase(movieDetailsRepository: MovieDetailsRepository) =
        FetchMovieCastUseCase(movieDetailsRepository)

    @Provides
    fun providesGetMoviesVideoUsecase(movieDetailsRepository: MovieDetailsRepository): FetchMovieVideoUseCase {
        return FetchMovieVideoUseCase(movieDetailsRepository)
    }

    @Provides
    fun providesFetchSimilarMoviesUsecase(movieDetailsRepository: MovieDetailsRepository): FetchSimilarMoviesUseCase {
        return FetchSimilarMoviesUseCase(movieDetailsRepository)
    }

    @Provides
    fun providesSettingsDataSource(
        themeDatastore: ThemeDatastore
    ): SettingsDataSource {
        return SettingsDataSource(themeDatastore)
    }

    @Provides
    fun providesSettingsRepository(settingsDataSource: SettingsDataSource): SettingsRepository {
        return SettingsRepository(settingsDataSource)
    }

    @Provides
    fun providesGetSavedThemeUseCase(settingsRepository: SettingsRepository): GetSavedThemeUseCase {
        return GetSavedThemeUseCase(settingsRepository)
    }

    @Provides
    fun providesSetThemeUseCase(settingsRepository: SettingsRepository): SelectThemeUseCase {
        return SelectThemeUseCase(settingsRepository)
    }

}


package com.vickikbt.domain.usecases

import com.vickikbt.domain.repositories.IPopularRepository
import com.vickikbt.domain.repositories.IUpcomingRepository

class FetchUpcomingMoviesUsecase constructor(private val upcomingRepository: IUpcomingRepository) {

    suspend operator fun invoke() = upcomingRepository.fetchUpcomingMovies()

}
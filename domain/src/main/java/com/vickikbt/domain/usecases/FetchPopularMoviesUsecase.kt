package com.vickikbt.domain.usecases

import com.vickikbt.domain.repositories.IPopularRepository

class FetchPopularMoviesUsecase constructor(private val popularRepository: IPopularRepository) {

    suspend operator fun invoke() = popularRepository.fetchPopularMovies()

}
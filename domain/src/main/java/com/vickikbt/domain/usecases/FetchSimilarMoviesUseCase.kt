package com.vickikbt.domain.usecases

import com.vickikbt.domain.repositories.IMovieDetailsRepository

class FetchSimilarMoviesUseCase constructor(private val movieDetailsRepository: IMovieDetailsRepository) {

    suspend operator fun invoke(movieId: Int) = movieDetailsRepository.getSimilarMovies(movieId)

}
package com.vickikbt.domain.usecases

import com.vickikbt.domain.repositories.IMovieDetailsRepository

class GetMovieDetailsUseCase constructor(private val movieDetailsRepository: IMovieDetailsRepository) {

    suspend operator fun invoke(movieId:Int) =movieDetailsRepository.fetchMovieDetails(movieId)

}
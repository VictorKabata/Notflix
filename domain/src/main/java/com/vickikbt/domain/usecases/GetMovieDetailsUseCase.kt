package com.vickikbt.domain.usecases

import com.vickikbt.domain.repositories.IMovieDetailsRepository

class GetMovieDetailsUseCase constructor(private val movieDetailsRepository: IMovieDetailsRepository) {

    //TODO:
    //suspend operator fun invoke(movieId:Int) =movieDetailsRepository.getMovieDetails(movieId)

}
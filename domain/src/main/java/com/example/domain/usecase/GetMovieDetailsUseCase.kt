package com.example.domain.usecase

import com.example.data.repository.contract.MovieRepository

class GetMovieDetailsUseCase(private val movieRepository: MovieRepository) {

    operator fun invoke(movieId: Int) = movieRepository.getMovieDetailsById(movieId)

}
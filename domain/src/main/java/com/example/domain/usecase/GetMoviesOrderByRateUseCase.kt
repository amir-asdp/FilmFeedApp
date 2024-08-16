package com.example.domain.usecase

import com.example.data.repository.contract.MovieRepository

class GetMoviesOrderByRateUseCase(private val movieRepository: MovieRepository) {

    operator fun invoke() = movieRepository.getMoviesOrderByRate()

}
package com.example.domain.usecase

import com.example.data.repository.contract.MovieRepository

class SearchMoviesUseCase(private val movieRepository: MovieRepository) {

    operator fun invoke(searchQuery: String) = movieRepository.searchMovies(searchQuery)

}
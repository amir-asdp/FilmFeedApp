package com.example.domain.di

import com.example.domain.usecase.GetMovieDetailsUseCase
import com.example.domain.usecase.GetMoviesOrderByRateUseCase
import com.example.domain.usecase.SearchMoviesUseCase
import org.koin.dsl.module

val domainModule = module {
    single { GetMoviesOrderByRateUseCase(get()) }
    single { GetMovieDetailsUseCase(get()) }
    single { SearchMoviesUseCase(get()) }
}
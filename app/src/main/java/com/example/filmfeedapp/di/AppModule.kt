package com.example.filmfeedapp.di

import com.example.domain.usecase.GetMovieDetailsUseCase
import com.example.domain.usecase.GetMoviesOrderByRateUseCase
import com.example.domain.usecase.SearchMoviesUseCase
import com.example.filmfeedapp.ui.viewmodel.DetailsViewModel
import com.example.filmfeedapp.ui.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { GetMoviesOrderByRateUseCase(get()) }
    single { GetMovieDetailsUseCase(get()) }
    single { SearchMoviesUseCase(get()) }
    viewModel { HomeViewModel(get(), get()) }
    viewModel { DetailsViewModel(get()) }
}
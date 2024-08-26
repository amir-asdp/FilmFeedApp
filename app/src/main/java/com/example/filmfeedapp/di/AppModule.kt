package com.example.filmfeedapp.di

import com.example.filmfeedapp.ui.viewmodel.DetailsViewModel
import com.example.filmfeedapp.ui.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { HomeViewModel(get(), get()) }
    viewModel { DetailsViewModel(get()) }
}
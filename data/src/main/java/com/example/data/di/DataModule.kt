package com.example.data.di

import com.example.data.repository.contract.MovieRepository
import com.example.data.repository.implementation.MovieRepositoryImpl
import com.example.data.source.remote.MovieRemoteDataSource
import com.example.data.source.remote.RetrofitDataSourceBuilder
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module
import kotlin.coroutines.CoroutineContext

val dataModule = module { 
    single { RetrofitDataSourceBuilder.buildDataSource(MovieRemoteDataSource::class.java) }
    single<CoroutineContext> { Dispatchers.IO }
    single<MovieRepository> { MovieRepositoryImpl(get(), get()) }
}
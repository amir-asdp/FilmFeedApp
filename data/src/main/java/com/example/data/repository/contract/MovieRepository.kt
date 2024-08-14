package com.example.data.repository.contract

import androidx.paging.PagingData
import com.example.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMoviesOrderByRate(): Flow<PagingData<Movie>>

}
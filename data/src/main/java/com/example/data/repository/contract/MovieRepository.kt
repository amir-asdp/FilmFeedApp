package com.example.data.repository.contract

import androidx.paging.PagingData
import com.example.data.model.common.MovieBrief
import com.example.data.model.common.MovieDetail
import com.example.data.model.common.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMoviesOrderByRate(): Flow<PagingData<MovieBrief>>

    fun getMovieDetailsById(movieId: Int): Flow<ResultWrapper<MovieDetail>>

    fun searchMovies(searchQuery: String): Flow<PagingData<MovieBrief>>

}
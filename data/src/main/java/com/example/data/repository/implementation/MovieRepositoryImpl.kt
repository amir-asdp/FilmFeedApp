package com.example.data.repository.implementation

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.constant.Constants.RemoteDataSource.ApiQueryParam
import com.example.data.model.common.MovieBrief
import com.example.data.model.common.MovieDetail
import com.example.data.model.common.ResultWrapper
import com.example.data.repository.contract.MovieRepository
import com.example.data.source.paging.MovieOrderByRatePagingSource
import com.example.data.source.paging.MovieSearchPagingSource
import com.example.data.source.remote.MovieRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val coroutineContext: CoroutineContext
): MovieRepository {

    override fun getMoviesOrderByRate(): Flow<PagingData<MovieBrief>> {
        return Pager(
            config = PagingConfig(20, 2, true),
            pagingSourceFactory = { MovieOrderByRatePagingSource(movieRemoteDataSource, coroutineContext) }
        ).flow
    }

    override fun getMovieDetailsById(movieId: Int): Flow<ResultWrapper<MovieDetail>> = flow {
        withContext(coroutineContext){
            try {
                val response = movieRemoteDataSource.getDetailsById(ApiQueryParam.API_KEY_VALUE, movieId)
                ResultWrapper.Success(MovieDetail.toMovieDetail(response.body()!!))
            }
            catch (e: Exception){
                ResultWrapper.CancelOrFailure(e)
            }
        }
    }

    override fun searchMovies(searchQuery: String): Flow<PagingData<MovieBrief>> {
        return Pager(
            config = PagingConfig(20, 2, true),
            pagingSourceFactory = { MovieSearchPagingSource(movieRemoteDataSource, coroutineContext, searchQuery) }
        ).flow
    }

}
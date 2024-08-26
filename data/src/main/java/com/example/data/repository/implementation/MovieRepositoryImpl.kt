package com.example.data.repository.implementation

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.data.constant.Constants.DataSourceRemote.ApiQueryParam
import com.example.data.model.ResultWrapper
import com.example.data.model.business.MovieBrief
import com.example.data.model.business.MovieDetail
import com.example.data.repository.contract.MovieRepository
import com.example.data.source.local.ImageCacheManager
import com.example.data.source.local.LocalDataSource
import com.example.data.source.paging.MovieOrderByRateRemoteMediator
import com.example.data.source.paging.MovieSearchPagingSource
import com.example.data.source.remote.MovieRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlin.coroutines.CoroutineContext

class MovieRepositoryImpl(
    private val coroutineContext: CoroutineContext,
    private val imageCacheManager: ImageCacheManager,
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val localDataSource: LocalDataSource,
): MovieRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getMoviesOrderByRate(): Flow<PagingData<MovieBrief>> {
        return Pager(
            config = PagingConfig(20, 2, true),
            remoteMediator = MovieOrderByRateRemoteMediator(
                imageCacheManager,
                movieRemoteDataSource,
                localDataSource
            ),
            pagingSourceFactory = { localDataSource.movieBriefEntityDao().getAllMovies() }
        ).flow.map { pagingData -> pagingData.map { MovieBrief.toMovieBrief(it) } }
    }


    override fun getMovieDetailsById(movieId: Int): Flow<ResultWrapper<MovieDetail>> = flow {
        try {
            val response = movieRemoteDataSource.getDetailsById(movieId, ApiQueryParam.API_KEY_VALUE)
            ResultWrapper.Success(MovieDetail.toMovieDetail(response.body()!!))
        }
        catch (e: Exception){
            ResultWrapper.CancelOrFailure(e)
        }.also {
            emit(it)
        }
    }.flowOn(coroutineContext)


    override fun searchMovies(searchQuery: String): Flow<PagingData<MovieBrief>> {
        return Pager(
            config = PagingConfig(20, 2, true),
            pagingSourceFactory = { MovieSearchPagingSource(searchQuery, coroutineContext, movieRemoteDataSource) }
        ).flow
    }

}
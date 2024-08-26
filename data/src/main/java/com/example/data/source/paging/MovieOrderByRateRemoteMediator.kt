package com.example.data.source.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.data.constant.Constants.DataSourceRemote.ApiQueryParam
import com.example.data.constant.Constants.DataSourceRemote.ApiUrl
import com.example.data.model.local.MovieBriefEntity
import com.example.data.source.local.ImageCacheManager
import com.example.data.source.local.LocalDataSource
import com.example.data.source.remote.MovieRemoteDataSource

@OptIn(ExperimentalPagingApi::class)
class MovieOrderByRateRemoteMediator(
    private val imageCacheManager: ImageCacheManager,
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val localDataSource: LocalDataSource,
) : RemoteMediator<Int, MovieBriefEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieBriefEntity>
    ): MediatorResult {
        return try {
            val page = when(loadType){
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> state.pages.lastOrNull()?.nextKey ?: return MediatorResult.Success(endOfPaginationReached = true)
            }

            val movieResponse = movieRemoteDataSource.getTopRated(ApiQueryParam.API_KEY_VALUE, page ?: 1)
            val movieList = movieResponse.body()?.results?.map {
                MovieBriefEntity(
                    id = it?.id?:1,
                    title = it?.title.toString(),
                    posterPhotoPath = imageCacheManager.cacheImage(ApiUrl.IMAGE_BASE_URL + it?.posterPath)
                )
            }?: mutableListOf()

            localDataSource.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    localDataSource.movieBriefEntityDao().clearAll()
                }

                localDataSource.movieBriefEntityDao().insertAll(movieList)
            }

            MediatorResult.Success(endOfPaginationReached = movieList.isEmpty())
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

}
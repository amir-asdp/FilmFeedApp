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
import retrofit2.HttpException
import java.io.IOException

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

            val currentLastPage: Int? = when(loadType){
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    state.pages.size
                }
            }

            val movieResponse = movieRemoteDataSource.getTopRated(ApiQueryParam.API_KEY_VALUE, currentLastPage?.plus(1) ?: 1)
            val movieList = movieResponse.body()?.results?.map {
                MovieBriefEntity(
                    id = it?.id.toString(),
                    title = it?.title.toString(),
                    posterPhotoPath = imageCacheManager.cacheImage(ApiUrl.IMAGE_BASE_URL_W500 + it?.posterPath)
                )
            }?: listOf()

            localDataSource.withTransaction {
                localDataSource.movieBriefEntityDao().insertAll(movieList)
            }


            MediatorResult.Success(endOfPaginationReached = movieList.isEmpty())

        }
        catch (e: IOException){
            MediatorResult.Error(e)
        }
        catch (e: HttpException){
            MediatorResult.Error(e)
        }

    }

}
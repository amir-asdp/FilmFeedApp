package com.example.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.constant.Constants.RemoteDataSource.ApiQueryParam
import com.example.data.model.common.MovieBrief
import com.example.data.source.remote.MovieRemoteDataSource
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class MovieOrderByRatePagingSource(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val coroutineContext: CoroutineContext
) : PagingSource<Int, MovieBrief>() {

    override fun getRefreshKey(state: PagingState<Int, MovieBrief>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieBrief> {
        return withContext(coroutineContext){
            try {
                val currentPage = params.key ?: 1
                val response = movieRemoteDataSource.getTopRated(ApiQueryParam.API_KEY_VALUE, currentPage)
                val loadedList = response.body()?.results?.map { MovieBrief.toMovieBrief(it!!) } ?: listOf()
                LoadResult.Page(
                    data = loadedList,
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = if (loadedList.isEmpty()) null else response.body()!!.page!! + 1
                )
            }
            catch (e: Exception){
                LoadResult.Error(e)
            }
        }
    }

}
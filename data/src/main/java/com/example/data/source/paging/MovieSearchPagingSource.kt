package com.example.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.constant.Constants.DataSourceRemote.ApiQueryParam
import com.example.data.model.business.MovieBrief
import com.example.data.source.remote.MovieRemoteDataSource
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import kotlin.coroutines.CoroutineContext

class MovieSearchPagingSource(
    private val searchQuery: String,
    private val coroutineContext: CoroutineContext,
    private val movieRemoteDataSource: MovieRemoteDataSource,
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
                val response = movieRemoteDataSource.search(ApiQueryParam.API_KEY_VALUE, currentPage, searchQuery)
                val loadedList = response.body()?.results?.map { MovieBrief.toMovieBrief(it!!) } ?: listOf()
                LoadResult.Page(
                    data = loadedList,
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = if (loadedList.isEmpty()) null else currentPage + 1
                )
            }
            catch (e: IOException){
                LoadResult.Error(e)
            }
            catch (e: HttpException){
                LoadResult.Error(e)
            }
        }
    }

}
package com.example.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.constant.Constants.RemoteDataSource.QueryParam
import com.example.data.source.remote.MovieRemoteDataSource
import com.example.domain.model.Movie

class MovieSearchPagingSource(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val searchQuery: String
) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val currentPage = params.key ?: 1
            val response = movieRemoteDataSource.search(QueryParam.API_KEY_VALUE, currentPage, searchQuery)
            LoadResult.Page(
                data = response.body()!!.results!!.map{
                    Movie(
                        it?.id.toString(),
                        it?.originalTitle.toString(),
                        it?.posterPath.toString()
                    )
                },
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (response.body()!!.results!!.isEmpty()) null else response.body()!!.page!! + 1
            )
        }
        catch (e: Exception){
            LoadResult.Error(e)
        }
    }

}
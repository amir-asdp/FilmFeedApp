package com.example.data.source.remote

import com.example.data.constant.Constants.RemoteDataSource.Url.Movie
import com.example.data.constant.Constants.RemoteDataSource.QueryParam
import com.example.data.model.remote.MovieDetailsResponse
import com.example.data.model.remote.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieRemoteDataSource {

    @GET(Movie.GET_TOP_RATED)
    suspend fun getTopRated(
        @Query(QueryParam.API_KEY_KEY) apiKey: String,
        @Query(QueryParam.PAGE) page: Int
    ): Response<MovieListResponse>

    @GET(Movie.GET_DETAILS)
    suspend fun getDetailsById(
        @Query(QueryParam.API_KEY_KEY) apiKey: String,
        @Path(Movie.PATH_MOVIE_ID) movieId: Int
    ): Response<MovieDetailsResponse>

    @GET(Movie.GET_SEARCH)
    suspend fun search(
        @Query(QueryParam.API_KEY_KEY) apiKey: String,
        @Query(QueryParam.PAGE) page: Int,
        @Query(QueryParam.SEARCH_QUERY) query: String
    ): Response<MovieListResponse>

}
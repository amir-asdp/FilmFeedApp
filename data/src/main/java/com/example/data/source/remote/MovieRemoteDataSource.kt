package com.example.data.source.remote

import com.example.data.constant.Constants.DataSourceRemote.ApiUrl.Movie
import com.example.data.constant.Constants.DataSourceRemote.ApiQueryParam
import com.example.data.model.remote.MovieDetailsResponse
import com.example.data.model.remote.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieRemoteDataSource {

    @GET(Movie.GET_TOP_RATED)
    suspend fun getTopRated(
        @Query(ApiQueryParam.API_KEY_KEY) apiKey: String,
        @Query(ApiQueryParam.PAGE) page: Int
    ): Response<MovieListResponse>

    @GET(Movie.GET_DETAILS)
    suspend fun getDetailsById(
        @Path(Movie.PATH_MOVIE_ID) movieId: Int,
        @Query(ApiQueryParam.API_KEY_KEY) apiKey: String
    ): Response<MovieDetailsResponse>

    @GET(Movie.GET_SEARCH)
    suspend fun search(
        @Query(ApiQueryParam.API_KEY_KEY) apiKey: String,
        @Query(ApiQueryParam.PAGE) page: Int,
        @Query(ApiQueryParam.SEARCH_QUERY) query: String
    ): Response<MovieListResponse>

}
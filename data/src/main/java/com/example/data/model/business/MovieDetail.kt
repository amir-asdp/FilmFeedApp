package com.example.data.model.business

import com.example.data.constant.Constants.DataSourceRemote.ApiUrl
import com.example.data.model.remote.MovieDetailsResponse

data class MovieDetail(
    val id: String,
    val title: String,
    val posterPhotoUrl: String,
    val status: String,
    val country: String,
    val language: String,
    val releaseDate: String,
    val overview: String,
    val genres: List<String>,
    val budget: String,
    val voteCount: String,
    val voteAverage: String
){
    companion object {
        fun toMovieDetail(movieDetailsResponse: MovieDetailsResponse): MovieDetail{
            return movieDetailsResponse.let {
                MovieDetail(
                    it.id.toString(),
                    it.originalTitle.toString(),
                    (ApiUrl.IMAGE_BASE_URL + it.posterPath),
                    it.status.toString(),
                    it.originCountry?.get(0).toString(),
                    it.originalLanguage.toString(),
                    it.releaseDate.toString(),
                    it.overview.toString(),
                    it.genres?.map { genre -> genre?.name.toString() } ?: listOf(),
                    it.budget.toString(),
                    it.voteCount.toString(),
                    it.voteAverage.toString()
                )
            }
        }
    }
}

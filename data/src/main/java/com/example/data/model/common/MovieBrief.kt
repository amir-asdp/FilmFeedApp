package com.example.data.model.common

import com.example.data.constant.Constants.RemoteDataSource.ApiUrl
import com.example.data.model.remote.MovieListResponse

data class MovieBrief(
    val id: String,
    val title: String,
    val posterPhotoUrl: String
){
    constructor() : this("-", "-", "-")

    companion object {
        fun toMovieBrief(movieListResponseResultItem: MovieListResponse.Result): MovieBrief{
            return movieListResponseResultItem.let {
                MovieBrief(
                    it.id.toString(),
                    it.originalTitle.toString(),
                    (ApiUrl.IMAGE_BASE_URL + it.posterPath)
                )
            }
        }
    }
}

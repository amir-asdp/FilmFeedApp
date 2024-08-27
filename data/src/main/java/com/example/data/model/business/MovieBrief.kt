package com.example.data.model.business

import com.example.data.constant.Constants.DataSourceRemote.ApiUrl
import com.example.data.model.local.MovieBriefEntity
import com.example.data.model.remote.MovieListResponse

data class MovieBrief(
    val id: String,
    val title: String,
    val posterPhotoUrlOrPath: String
){
    constructor() : this("-", "-", "-")

    companion object {
        fun toMovieBrief(movieListResponseResultItem: MovieListResponse.Result): MovieBrief{
            return movieListResponseResultItem.let {
                MovieBrief(
                    it.id.toString(),
                    it.title.toString(),
                    (ApiUrl.IMAGE_BASE_URL_W500 + it.posterPath)
                )
            }
        }

        fun toMovieBrief(movieBriefEntity: MovieBriefEntity): MovieBrief{
            return movieBriefEntity.let {
                MovieBrief(
                    it.id,
                    it.title,
                    it.posterPhotoPath
                )
            }
        }
    }
}

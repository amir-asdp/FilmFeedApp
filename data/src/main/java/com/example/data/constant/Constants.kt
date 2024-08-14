package com.example.data.constant

object Constants {

    object RemoteDataSource{
        object Url{
            const val API_BASE_URL = "https://api.themoviedb.org/3"
            const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/original"

            object Movie{
                const val GET_TOP_RATED = "/movie/top_rated"
                const val GET_POPULAR = "/movie/popular"
                const val GET_DETAILS = "/movie/{movie_id}"
                const val GET_SEARCH = "/search/movie"
                const val PATH_MOVIE_ID = "movie_id"
            }
        }

        object QueryParam{
            const val API_KEY_KEY = "api_key"
            const val API_KEY_VALUE = "be0f87b7ce8457acdd85bb1c0b756fbe"
            const val PAGE = "page"
            const val SEARCH_QUERY = "query"
        }
    }

    object LocalDataSource{

    }

}
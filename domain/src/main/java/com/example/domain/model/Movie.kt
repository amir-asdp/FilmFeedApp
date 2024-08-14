package com.example.domain.model

data class Movie(
    val id: String,
    val title: String,
    val posterPhotoUrl: String,
    val status: String? = null,
    val country: String? = null,
    val language: String? = null,
    val releaseDate: String? = null,
    val overview: String? = null,
    val genres: List<String>? = null,
    val budget: Int? = null,
    val voteCount: Int? = null,
    val voteAverage: Float? = null
)

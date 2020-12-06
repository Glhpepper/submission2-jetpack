package com.example.moviescatalogue.data.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseDetailMovies(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("genres")
    val genres: List<GenresItemMovies>?,

    @SerializedName("popularity")
    val popularity: Double?,

    @SerializedName("vote_count")
    val voteCount: Int?,

    @SerializedName("overview")
    val overview: String?,

    @SerializedName("original_title")
    val originalTitle: String?,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("backdrop_path")
    val backdropPath: String?,

    @SerializedName("release_date")
    val releaseDate: String?,

    @SerializedName("vote_average")
    val voteAverage: Double?,

    @SerializedName("tagline")
    val tagline: String?,

    @SerializedName("homepage")
    val homepage: String?,

    @SerializedName("status")
    val status: String?
)

data class GenresItemMovies(

    @SerializedName("name")
    val name: String?
)
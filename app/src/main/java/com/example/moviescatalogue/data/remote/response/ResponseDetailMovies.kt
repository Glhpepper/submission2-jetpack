package com.example.moviescatalogue.data.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseDetailMovies(

    @field:SerializedName("title")
    val title: String?,

    @field:SerializedName("genres")
    val genres: List<GenresItemMovies>?,

    @field:SerializedName("popularity")
    val popularity: Double?,

    @field:SerializedName("id")
    val id: Int?,

    @field:SerializedName("vote_count")
    val voteCount: Int?,

    @field:SerializedName("overview")
    val overview: String?,

    @field:SerializedName("original_title")
    val originalTitle: String?,

    @field:SerializedName("poster_path")
    val posterPath: String?,

    @field:SerializedName("backdrop_path")
    val backdropPath: String?,

    @field:SerializedName("release_date")
    val releaseDate: String?,

    @field:SerializedName("vote_average")
    val voteAverage: Double?,

    @field:SerializedName("tagline")
    val tagline: String?,

    @field:SerializedName("homepage")
    val homepage: String?,

    @field:SerializedName("status")
    val status: String?
)

data class GenresItemMovies(

    @field:SerializedName("name")
    val name: String?
)
package com.example.moviescatalogue.data.remote.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class ResponseDetailShows(
    @PrimaryKey
    @SerializedName("id")
    val id: Int?,

    @SerializedName("genres")
    val genres: List<GenresItemShows>?,

    @SerializedName("popularity")
    val popularity: Double?,

    @SerializedName("vote_count")
    val voteCount: Int?,

    @SerializedName("first_air_date")
    val firstAirDate: String?,

    @SerializedName("overview")
    val overview: String?,

    @SerializedName("seasons")
    val seasons: List<SeasonsItem>?,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("original_name")
    val originalName: String?,

    @SerializedName("vote_average")
    val voteAverage: Double?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("tagline")
    val tagline: String?,

    @SerializedName("homepage")
    val homepage: String?,

    @SerializedName("status")
    val status: String?
)

data class GenresItemShows(

    @SerializedName("name")
    val name: String?,

    @SerializedName("id")
    val id: Int?
)

data class SeasonsItem(

    @SerializedName("name")
    val name: String?,

    @SerializedName("id")
    val id: Int?,

    @SerializedName("poster_path")
    val posterPath: String?
)


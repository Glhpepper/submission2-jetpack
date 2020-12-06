package com.example.moviescatalogue.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_movies")
data class FavoriteMovies(
    @PrimaryKey
    val id: Int?,
    val title: String?,
    val genres: ArrayList<String>?,
    val popularity: Double?,
    val voteCount: Int?,
    val overview: String?,
    val originalTitle: String?,
    val posterPath: String?,
    val backdropPath: String?,
    val releaseDate: String?,
    val voteAverage: Double?,
    val tagline: String?,
    val homepage: String?,
    val status: String?
)


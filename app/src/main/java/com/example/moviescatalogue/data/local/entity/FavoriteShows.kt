package com.example.moviescatalogue.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_shows")
data class FavoriteShows (
    @PrimaryKey
    val id: Int?,
    val genres: ArrayList<String>?,
    val popularity: Double?,
    val voteCount: Int?,
    val firstAirDate: String?,
    val overview: String?,
    val seasons: ArrayList<String>?,
    val posterPath: String?,
    val originalName: String?,
    val voteAverage: Double?,
    val name: String?,
    val tagline: String?,
    val homepage: String?,
    val status: String?
)
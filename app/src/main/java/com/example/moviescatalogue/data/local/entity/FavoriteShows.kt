package com.example.moviescatalogue.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviescatalogue.data.remote.response.GenresItemShows
import com.example.moviescatalogue.data.remote.response.SeasonsItem

@Entity(tableName = "favorite_shows")
data class FavoriteShows (
    @PrimaryKey
    val id: Int?,
    val genres: List<GenresItemShows>?,
    val popularity: Double?,
    val voteCount: Int?,
    val firstAirDate: String?,
    val overview: String?,
    val seasons: List<SeasonsItem>?,
    val posterPath: String?,
    val originalName: String?,
    val voteAverage: String?,
    val name: String?,
    val tagline: String?,
    val homepage: String?,
    val status: String?
)
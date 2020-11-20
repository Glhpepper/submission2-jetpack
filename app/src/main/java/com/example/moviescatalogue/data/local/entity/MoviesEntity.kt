package com.example.moviescatalogue.data.local.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MoviesEntity(

    @field:SerializedName("id")
    val moviesId: String,

    @field:SerializedName("title")
    val moviesTitle: String,

    @field:SerializedName("overview")
    val moviesOverview: String,

    @field:SerializedName("vote_average")
    val moviesScore: String,

    @field:SerializedName("poster_path")
    val moviesImage: String,

    @field:SerializedName("release_date")
    val moviesDate:String
) : Parcelable
package com.example.moviescatalogue.data.local.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MoviesEntity(

    @SerializedName("id")
    val moviesId: String,

    @SerializedName("title")
    val moviesTitle: String,

    @SerializedName("overview")
    val moviesOverview: String,

    @SerializedName("vote_average")
    val moviesScore: String,

    @SerializedName("poster_path")
    val moviesImage: String,

    @SerializedName("release_date")
    val moviesDate:String
) : Parcelable
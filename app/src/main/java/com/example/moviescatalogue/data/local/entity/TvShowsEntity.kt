package com.example.moviescatalogue.data.local.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShowsEntity(

    @field:SerializedName("id")
    val showsId: String,

    @field:SerializedName("name")
    val showsTitle: String,

    @field:SerializedName("overview")
    val showsOverview: String,

    @field:SerializedName("vote_average")
    val showsScore:String,

    @field:SerializedName("poster_path")
    val showsImage: String,

    @field:SerializedName("first_air_date")
    val showsDate:String
) : Parcelable
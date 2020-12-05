package com.example.moviescatalogue.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "shows")
data class TvShowsEntity(
    @PrimaryKey
    @SerializedName("id")
    val showsId: String,

    @SerializedName("name")
    val showsTitle: String,

    @SerializedName("overview")
    val showsOverview: String,

    @SerializedName("vote_average")
    val showsScore:String,

    @SerializedName("poster_path")
    val showsImage: String,

    @SerializedName("first_air_date")
    val showsDate:String
) : Parcelable
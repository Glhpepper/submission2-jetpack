package com.example.moviescatalogue.data.remote.response

import com.example.moviescatalogue.data.local.entity.TvShowsEntity
import com.google.gson.annotations.SerializedName

data class ResponseTvShows(
    @field:SerializedName("results")
    val results: List<TvShowsEntity>?,
)
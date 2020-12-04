package com.example.moviescatalogue.data.remote.response

import com.example.moviescatalogue.data.local.entity.MoviesEntity
import com.google.gson.annotations.SerializedName


data class ResponseMovies(
	@field:SerializedName("results")
	val results: List<MoviesEntity>,
)
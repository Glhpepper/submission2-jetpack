package com.example.moviescatalogue.data.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseDetailShows(

	@field:SerializedName("genres")
	val genres: List<GenresItemShows>?,

	@field:SerializedName("popularity")
	val popularity: Double?,

	@field:SerializedName("id")
	val id: Int?,

	@field:SerializedName("vote_count")
	val voteCount: Int?,

	@field:SerializedName("first_air_date")
	val firstAirDate: String?,

	@field:SerializedName("overview")
	val overview: String?,

	@field:SerializedName("seasons")
	val seasons: List<SeasonsItem>?,

	@field:SerializedName("poster_path")
	val posterPath: String?,

	@field:SerializedName("original_name")
	val originalName: String?,

	@field:SerializedName("vote_average")
	val voteAverage: Double?,

	@field:SerializedName("name")
	val name: String?,

	@field:SerializedName("tagline")
	val tagline: String?,

	@field:SerializedName("homepage")
	val homepage: String?,

	@field:SerializedName("status")
	val status: String?
)

data class GenresItemShows(

	@field:SerializedName("name")
	val name: String?,

	@field:SerializedName("id")
	val id: Int?
)

data class SeasonsItem(

	@field:SerializedName("name")
	val name: String?,

	@field:SerializedName("id")
	val id: Int?,

	@field:SerializedName("poster_path")
	val posterPath: String?
)


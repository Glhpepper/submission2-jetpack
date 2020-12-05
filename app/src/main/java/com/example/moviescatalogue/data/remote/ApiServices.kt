package com.example.moviescatalogue.data.remote

import com.example.moviescatalogue.BuildConfig
import com.example.moviescatalogue.data.remote.response.ResponseDetailMovies
import com.example.moviescatalogue.data.remote.response.ResponseDetailShows
import com.example.moviescatalogue.data.remote.response.ResponseMovies
import com.example.moviescatalogue.data.remote.response.ResponseTvShows
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {
    @GET("discover/movie?")
    suspend fun getListMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_TOKEN,
        @Query("language") language : String = "en-EN",
        @Query("page") page: Int
    ): ResponseMovies

    @GET("discover/tv?")
    suspend fun getListTvShows(
        @Query("api_key") apiKey: String = BuildConfig.API_TOKEN,
        @Query("language") language : String = "en-EN",
        @Query("page") page: Int
    ): ResponseTvShows

    @GET("movie/{id}?api_key=${BuildConfig.API_TOKEN}")
    suspend fun getDetailMovies(@Path("id") id: String): ResponseDetailMovies

    @GET("tv/{id}?api_key=${BuildConfig.API_TOKEN}")
    suspend fun getDetailTvShows(@Path("id") id: String): ResponseDetailShows
}
package com.example.moviescatalogue.data.remote.api

import com.example.moviescatalogue.BuildConfig
import com.example.moviescatalogue.data.remote.response.ResponseDetailMovies
import com.example.moviescatalogue.data.remote.response.ResponseDetailShows
import com.example.moviescatalogue.data.remote.response.ResponseMovies
import com.example.moviescatalogue.data.remote.response.ResponseTvShows
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServices {

    @GET("discover/movie?api_key=${BuildConfig.API_TOKEN}&sort_by=popularity.desc&page=1")
    suspend fun getListMovie(): ResponseMovies

    @GET("discover/tv?api_key=${BuildConfig.API_TOKEN}&sort_by=popularity.desc&page=1")
    suspend fun getListTvShow(): ResponseTvShows

    @GET("movie/{id}?api_key=${BuildConfig.API_TOKEN}")
    suspend fun getDetailMovies(@Path("id") id: String): ResponseDetailMovies

    @GET("tv/{id}?api_key=${BuildConfig.API_TOKEN}")
    suspend fun getDetailTvShows(@Path("id") id: String): ResponseDetailShows
}
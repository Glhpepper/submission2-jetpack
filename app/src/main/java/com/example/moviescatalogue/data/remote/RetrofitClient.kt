package com.example.moviescatalogue.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.themoviedb.org/3/"
    const val BASE_IMG = "https://image.tmdb.org/t/p/w600_and_h900_bestv2"
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    private val services: ApiServices by lazy {
        retrofit.create(ApiServices::class.java)
    }

    fun getNetworkService() = services
}
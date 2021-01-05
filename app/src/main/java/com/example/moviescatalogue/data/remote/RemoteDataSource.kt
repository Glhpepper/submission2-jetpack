package com.example.moviescatalogue.data.remote

import com.example.moviescatalogue.data.remote.api.ApiServices
import com.example.moviescatalogue.data.remote.response.ResponseDetailMovies
import com.example.moviescatalogue.data.remote.response.ResponseDetailShows
import com.example.moviescatalogue.data.remote.response.ResponseMovies
import com.example.moviescatalogue.data.remote.response.ResponseTvShows
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiServices: ApiServices) {

    suspend fun getListMovies(): ResponseMovies = apiServices.getListMovie()

    suspend fun getDetailMovies(id: String): ResponseDetailMovies = apiServices.getDetailMovies(id)

    suspend fun getListShows(): ResponseTvShows = apiServices.getListTvShow()

    suspend fun getDetailShows(id: String): ResponseDetailShows = apiServices.getDetailTvShows(id)
}
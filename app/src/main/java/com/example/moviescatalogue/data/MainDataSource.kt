package com.example.moviescatalogue.data

import com.example.moviescatalogue.data.local.entity.DetailEntity
import com.example.moviescatalogue.data.local.entity.MoviesEntity
import com.example.moviescatalogue.data.local.entity.TvShowsEntity
import com.example.moviescatalogue.data.remote.response.ResponseDetailMovies
import com.example.moviescatalogue.data.remote.response.ResponseDetailShows
import com.example.moviescatalogue.data.remote.response.ResponseMovies
import com.example.moviescatalogue.data.remote.response.ResponseTvShows

interface MainDataSource {

    suspend fun getMovies(): ArrayList<MoviesEntity>

    suspend fun getTvShows(): ArrayList<TvShowsEntity>

    suspend fun getMoviesApi(): ResponseMovies

    suspend fun getShowsApi(): ResponseTvShows

    suspend fun getDetailMovie(id: String): ResponseDetailMovies

    suspend fun getDetailTvShows(id: String): ResponseDetailShows

    suspend fun getDetailOffline(id: String): ArrayList<DetailEntity>
}
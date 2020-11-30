package com.example.moviescatalogue.data

import androidx.lifecycle.LiveData
import com.example.moviescatalogue.data.local.entity.DetailEntity
import com.example.moviescatalogue.data.local.entity.MoviesEntity
import com.example.moviescatalogue.data.local.entity.TvShowsEntity
import com.example.moviescatalogue.data.remote.response.ResponseDetailMovies
import com.example.moviescatalogue.data.remote.response.ResponseDetailShows
import com.example.moviescatalogue.data.remote.response.ResponseMovies
import com.example.moviescatalogue.data.remote.response.ResponseTvShows

interface MainDataSource {

    suspend fun getMovies(): LiveData<List<MoviesEntity>>

    suspend fun getTvShows(): LiveData<List<TvShowsEntity>>

    suspend fun getMoviesApi(): LiveData<ResponseMovies>

    suspend fun getShowsApi(): LiveData<ResponseTvShows>

    suspend fun getDetailMovie(id: String): LiveData<ResponseDetailMovies>

    suspend fun getDetailTvShows(id: String): LiveData<ResponseDetailShows>

    suspend fun getDetailOffline(id: String): LiveData<DetailEntity>
}
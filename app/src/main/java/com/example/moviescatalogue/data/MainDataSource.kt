package com.example.moviescatalogue.data

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.moviescatalogue.data.local.entity.MoviesEntity
import com.example.moviescatalogue.data.remote.response.ResponseDetailMovies
import com.example.moviescatalogue.data.remote.response.ResponseDetailShows
import com.example.moviescatalogue.data.remote.response.ResponseMovies
import com.example.moviescatalogue.data.remote.response.ResponseTvShows

interface MainDataSource {

    suspend fun getMoviesApi(): LiveData<PagingData<MoviesEntity>>

    suspend fun getShowsApi(): LiveData<ResponseTvShows>

    suspend fun getDetailMovie(id: String): LiveData<ResponseDetailMovies>

    suspend fun getDetailTvShows(id: String): LiveData<ResponseDetailShows>
}
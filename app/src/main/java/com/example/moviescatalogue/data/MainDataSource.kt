package com.example.moviescatalogue.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.moviescatalogue.data.local.entity.FavoriteMovies
import com.example.moviescatalogue.data.local.entity.FavoriteShows
import com.example.moviescatalogue.data.remote.response.ResponseDetailMovies
import com.example.moviescatalogue.data.remote.response.ResponseDetailShows
import com.example.moviescatalogue.data.remote.response.ResponseMovies
import com.example.moviescatalogue.data.remote.response.ResponseTvShows

interface MainDataSource {

    suspend fun getMoviesApi(): LiveData<ResponseMovies>

    suspend fun getShowsApi(): LiveData<ResponseTvShows>

    suspend fun getDetailMovie(id: String): LiveData<ResponseDetailMovies>

    suspend fun getDetailTvShows(id: String): LiveData<ResponseDetailShows>

    suspend fun getMoviesFavoritePaging(): LiveData<PagedList<FavoriteMovies>>

    suspend fun checkFavoriteMovies(id: Int): Boolean

    suspend fun insertFavoriteMovies(favoriteMovies: FavoriteMovies)

    suspend fun deleteFavoriteMoviesById(id: Int)

    suspend fun deleteFavoriteMovies(favoriteMovies: FavoriteMovies)

    suspend fun getShowsFavoritePaging(): LiveData<PagedList<FavoriteShows>>

    suspend fun checkFavoriteShows(id: Int): Boolean

    suspend fun insertFavoriteShows(favoriteShows: FavoriteShows)

    suspend fun deleteFavoriteShowsById(id: Int)

    suspend fun deleteFavoriteShows(favoriteShows: FavoriteShows)
}
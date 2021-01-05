package com.example.moviescatalogue.data.local

import androidx.paging.DataSource
import com.example.moviescatalogue.data.local.entity.FavoriteMovies
import com.example.moviescatalogue.data.local.entity.FavoriteShows
import com.example.moviescatalogue.data.local.room.FavoriteMoviesDao
import com.example.moviescatalogue.data.local.room.FavoriteShowsDao
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val moviesDao: FavoriteMoviesDao,
    private val showsDao: FavoriteShowsDao
) {

    fun getAllFavoriteMovies(): DataSource.Factory<Int, FavoriteMovies> =
        moviesDao.getFavoriteMoviePaging()

    suspend fun checkFavoriteMovies(id: Int): Boolean = moviesDao.checkFavorite(id)

    fun insertFavoriteMovies(favoriteMovies: FavoriteMovies) =
        moviesDao.insertFavoriteMovies(favoriteMovies)

    fun deleteFavoriteMoviesById(id: Int) = moviesDao.deleteFavoriteById(id)

    suspend fun deleteFavoriteMovies(favoriteMovies: FavoriteMovies) =
        moviesDao.deleteFavoriteUser(favoriteMovies)

    fun getAllFavoriteShows(): DataSource.Factory<Int, FavoriteShows> =
        showsDao.getFavoriteMShowPaging()

    suspend fun checkFavoriteShows(id: Int): Boolean = showsDao.checkFavorite(id)

    fun insertFavoriteShows(favoriteShows: FavoriteShows) =
        showsDao.insertFavoriteShows(favoriteShows)

    fun deleteFavoriteShowsById(id: Int) = showsDao.deleteFavoriteById(id)

    suspend fun deleteFavoriteShows(favoriteShows: FavoriteShows) =
        showsDao.deleteFavoriteUser(favoriteShows)
}
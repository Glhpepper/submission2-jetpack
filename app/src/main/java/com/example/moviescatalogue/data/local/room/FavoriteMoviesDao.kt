package com.example.moviescatalogue.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.moviescatalogue.data.local.entity.FavoriteMovies
import com.example.moviescatalogue.data.remote.response.ResponseDetailMovies

@Dao
interface FavoriteMoviesDao {
    @Query("SELECT * from favorite_movies")
    fun getFavoriteMovies(): LiveData<List<FavoriteMovies>>

    @Query("SELECT EXISTS(SELECT * from favorite_movies WHERE id =:id)")
    suspend fun checkFavorite(id: Int): Boolean

    @Query("DELETE from favorite_movies WHERE id =:id")
    fun deleteFavoriteById(id: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteMovies(favoriteMovies: FavoriteMovies)

    @Delete
    suspend fun deleteFavoriteUser(favoriteMovies: FavoriteMovies)
}
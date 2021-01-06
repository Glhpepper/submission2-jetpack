package com.example.moviescatalogue.data.local.room

import androidx.paging.DataSource
import androidx.room.*
import com.example.moviescatalogue.data.local.entity.FavoriteShows

@Dao
interface FavoriteShowsDao {

    @Query("SELECT * FROM favorite_shows")
    fun getFavoriteMShowPaging(): DataSource.Factory<Int, FavoriteShows>

    @Query("SELECT EXISTS(SELECT * from favorite_shows WHERE id =:id)")
    suspend fun checkFavorite(id: Int): Boolean

    @Query("DELETE from favorite_shows WHERE id =:id")
    fun deleteFavoriteById(id: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteShows(favoriteShows: FavoriteShows)

    @Delete
    suspend fun deleteFavoriteUser(favoriteShows: FavoriteShows)
}
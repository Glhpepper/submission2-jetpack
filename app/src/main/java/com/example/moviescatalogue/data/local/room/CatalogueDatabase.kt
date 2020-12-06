package com.example.moviescatalogue.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.moviescatalogue.data.local.entity.FavoriteMovies
import com.example.moviescatalogue.data.local.entity.FavoriteShows
import com.example.moviescatalogue.data.remote.response.ResponseDetailMovies
import com.example.moviescatalogue.data.remote.response.ResponseDetailShows

@Database(
    entities = [FavoriteMovies::class, FavoriteShows::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class CatalogueDatabase : RoomDatabase() {

    abstract fun favoriteMovieDao(): FavoriteMoviesDao
    abstract fun favoriteShowsDao(): FavoriteShowsDao

}
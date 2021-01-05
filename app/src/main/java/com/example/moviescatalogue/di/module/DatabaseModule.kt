package com.example.moviescatalogue.di.module

import android.content.Context
import androidx.room.Room
import com.example.moviescatalogue.data.local.room.CatalogueDatabase
import com.example.moviescatalogue.data.local.room.FavoriteMoviesDao
import com.example.moviescatalogue.data.local.room.FavoriteShowsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDataBase(context: Context): CatalogueDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            CatalogueDatabase::class.java,
            "Catalogue.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(catalogueDatabase: CatalogueDatabase): FavoriteMoviesDao {
        return catalogueDatabase.favoriteMovieDao()
    }

    @Singleton
    @Provides
    fun provideShowsDao(catalogueDatabase: CatalogueDatabase): FavoriteShowsDao {
        return catalogueDatabase.favoriteShowsDao()
    }
}
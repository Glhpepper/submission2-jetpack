package com.example.moviescatalogue.di.module

import android.content.Context
import androidx.room.Room
import com.example.moviescatalogue.data.local.room.CatalogueDatabase
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
}
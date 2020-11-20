package com.example.moviescatalogue.di.module

import com.example.moviescatalogue.utils.DummyData
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DummyModule {
    @Singleton
    @Provides
    fun provideDummy(): DummyData = DummyData

}
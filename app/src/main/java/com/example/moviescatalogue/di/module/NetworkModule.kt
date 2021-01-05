package com.example.moviescatalogue.di.module

import com.example.moviescatalogue.data.remote.api.ApiServices
import com.example.moviescatalogue.data.remote.api.RetrofitClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideApi(): ApiServices {
        return RetrofitClient.getNetworkService()
    }
}
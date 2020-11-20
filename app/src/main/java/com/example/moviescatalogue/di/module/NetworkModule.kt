package com.example.moviescatalogue.di.module

import com.example.moviescatalogue.data.remote.ApiServices
import com.example.moviescatalogue.data.remote.RetrofitClient
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideApi(): ApiServices {
        return RetrofitClient.getNetworkService()
    }
}
package com.example.moviescatalogue.di

import androidx.paging.ExperimentalPagingApi
import com.example.moviescatalogue.ui.detail.di.DetailComponent
import com.example.moviescatalogue.ui.main.di.MainComponent
import dagger.Module

@Module(subcomponents = [MainComponent::class, DetailComponent::class])
class AppMainComponent
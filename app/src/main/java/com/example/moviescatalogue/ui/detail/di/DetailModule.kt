package com.example.moviescatalogue.ui.detail.di

import androidx.lifecycle.ViewModel
import com.example.moviescatalogue.di.module.ViewModelModuleBuilder.ViewModelKey
import com.example.moviescatalogue.ui.detail.DetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class DetailModule {

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindViewModel(viewModel: DetailViewModel): ViewModel
}
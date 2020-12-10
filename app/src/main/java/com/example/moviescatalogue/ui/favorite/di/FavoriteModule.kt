package com.example.moviescatalogue.ui.favorite.di

import androidx.lifecycle.ViewModel
import com.example.moviescatalogue.di.module.ViewModelModuleBuilder
import com.example.moviescatalogue.ui.favorite.FavoriteViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FavoriteModule {

    @Binds
    @IntoMap
    @ViewModelModuleBuilder.ViewModelKey(FavoriteViewModel::class)
    abstract fun bindViewModel(viewModel: FavoriteViewModel): ViewModel
}
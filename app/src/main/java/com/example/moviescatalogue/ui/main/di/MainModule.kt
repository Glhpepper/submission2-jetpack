package com.example.moviescatalogue.ui.main.di

import androidx.lifecycle.ViewModel
import com.example.moviescatalogue.di.module.ViewModelModuleBuilder.ViewModelKey
import com.example.moviescatalogue.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindViewModel(viewModel: MainViewModel): ViewModel
}
package com.example.moviescatalogue.di

import com.example.moviescatalogue.data.MainRepository
import com.example.moviescatalogue.di.module.DispatcherModule
import com.example.moviescatalogue.di.module.NetworkModule
import com.example.moviescatalogue.di.module.ViewModelModuleBuilder
import com.example.moviescatalogue.ui.detail.di.DetailComponent
import com.example.moviescatalogue.ui.main.di.MainComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DispatcherModule::class,
        NetworkModule::class,
        AppMainComponent::class,
        ViewModelModuleBuilder::class]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(): AppComponent
    }

    fun mainComponent(): MainComponent.Factory
    fun detailComponent(): DetailComponent.Factory

    val mainRepository: MainRepository
}
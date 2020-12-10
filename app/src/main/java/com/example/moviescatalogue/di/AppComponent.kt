package com.example.moviescatalogue.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import com.example.moviescatalogue.data.MainRepository
import com.example.moviescatalogue.di.module.DatabaseModule
import com.example.moviescatalogue.di.module.DispatcherModule
import com.example.moviescatalogue.di.module.NetworkModule
import com.example.moviescatalogue.di.module.ViewModelModuleBuilder
import com.example.moviescatalogue.ui.detail.di.DetailComponent
import com.example.moviescatalogue.ui.favorite.di.FavoriteComponent
import com.example.moviescatalogue.ui.main.di.MainComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DispatcherModule::class,
        NetworkModule::class,
        DatabaseModule::class,
        AppMainComponent::class,
        ViewModelModuleBuilder::class]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun mainComponent(): MainComponent.Factory
    fun detailComponent(): DetailComponent.Factory
    fun favoriteComponent(): FavoriteComponent.Factory

    val mainRepository: MainRepository
}
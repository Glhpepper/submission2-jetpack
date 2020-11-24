package com.example.moviescatalogue.di

import com.example.moviescatalogue.di.module.DispatcherModule
import com.example.moviescatalogue.di.module.DummyModule
import com.example.moviescatalogue.di.module.NetworkModule
import com.example.moviescatalogue.di.module.ViewModelModuleBuilder
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DispatcherModule::class,
        NetworkModule::class,
        DummyModule::class,
        AppMainComponent::class,
        ViewModelModuleBuilder::class]
)
interface TestAppComponent : AppComponent {

    @Component.Factory
    interface Factory {
        fun create(): TestAppComponent
    }

}
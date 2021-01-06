package com.example.moviescatalogue.di

import android.content.Context
import com.example.moviescatalogue.di.module.*
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
interface TestAppComponent : AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): TestAppComponent
    }

}
package com.example.moviescatalogue.ui.detail.di

import com.example.moviescatalogue.ui.detail.DetailActivity
import com.example.moviescatalogue.ui.main.di.MainComponent
import dagger.Subcomponent

@DetailScope
@Subcomponent(modules = [DetailModule::class])
interface DetailComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): DetailComponent
    }

    fun inject(activity: DetailActivity)
}
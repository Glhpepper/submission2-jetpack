package com.example.moviescatalogue.ui.main.di

import com.example.moviescatalogue.ui.main.MainActivity
import com.example.moviescatalogue.ui.main.MainFragment
import com.example.moviescatalogue.ui.main.movies.MoviesFragment
import com.example.moviescatalogue.ui.main.tvshows.TvShowsFragment
import dagger.Subcomponent

@MainScope
@Subcomponent(modules = [MainModule::class])
interface MainComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(activity: MainActivity)
    fun inject(fragment: MainFragment)
    fun inject(fragment: MoviesFragment)
    fun inject(fragment: TvShowsFragment)
}
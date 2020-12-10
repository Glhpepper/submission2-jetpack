package com.example.moviescatalogue.ui.favorite.di

import com.example.moviescatalogue.ui.favorite.FavoriteFragment
import com.example.moviescatalogue.ui.favorite.movies.FavoriteMoviesFragment
import com.example.moviescatalogue.ui.favorite.tvshows.FavoriteShowsFragment
import dagger.Subcomponent

@FavoriteScope
@Subcomponent(modules = [FavoriteModule::class])
interface FavoriteComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): FavoriteComponent
    }

    fun inject(fragment: FavoriteFragment)
    fun inject(fragment: FavoriteMoviesFragment)
    fun inject(fragment: FavoriteShowsFragment)
}
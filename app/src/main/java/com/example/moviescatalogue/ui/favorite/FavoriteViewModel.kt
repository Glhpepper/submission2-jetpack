package com.example.moviescatalogue.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.example.moviescatalogue.data.MainRepository
import com.example.moviescatalogue.data.local.entity.FavoriteMovies
import com.example.moviescatalogue.data.local.entity.FavoriteShows
import com.example.moviescatalogue.ui.favorite.di.FavoriteScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@FavoriteScope
class FavoriteViewModel @Inject constructor(private val mainRepository: MainRepository) :
    ViewModel() {

    suspend fun getFavoriteMoviePaging(): LiveData<PagedList<FavoriteMovies>> =
        mainRepository.getMoviesFavoritePaging()

    suspend fun getFavoriteShowPaging(): LiveData<PagedList<FavoriteShows>> =
        mainRepository.getShowsFavoritePaging()

    fun deleteFavoriteMovies(favoriteMovies: FavoriteMovies?) {
        viewModelScope.launch {
            if (favoriteMovies != null) {
                mainRepository.deleteFavoriteMovies(favoriteMovies)
            }
        }
    }

    fun deleteFavoriteShows(favoriteShows: FavoriteShows?) {
        viewModelScope.launch {
            if (favoriteShows != null) {
                mainRepository.deleteFavoriteShows(favoriteShows)
            }
        }
    }
}
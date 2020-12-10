package com.example.moviescatalogue.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviescatalogue.data.MainRepository
import com.example.moviescatalogue.data.local.entity.FavoriteMovies
import com.example.moviescatalogue.data.local.entity.FavoriteShows
import com.example.moviescatalogue.data.local.entity.MoviesEntity
import com.example.moviescatalogue.ui.favorite.di.FavoriteScope
import com.example.moviescatalogue.utils.wrapEspressoIdlingResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@FavoriteScope
class FavoriteViewModel @Inject constructor(private val mainRepository: MainRepository) :
    ViewModel() {

    private lateinit var _favoriteMovies: LiveData<PagingData<FavoriteMovies>>
    val favoriteMovies
        get() = _favoriteMovies

    private lateinit var _favoriteShows: LiveData<PagingData<FavoriteShows>>
    val favoriteShows
        get() = _favoriteShows

    suspend fun getFavoriteMoviePaging() {
        wrapEspressoIdlingResource {
            val movieList = mainRepository.getMoviesFavoritePaging().cachedIn(viewModelScope)
            try {
                _favoriteMovies = movieList
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    suspend fun getFavoriteShowsPaging() {
        wrapEspressoIdlingResource {
            val showsList = mainRepository.getShowsFavoritePaging().cachedIn(viewModelScope)
            try {
                _favoriteShows = showsList
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

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
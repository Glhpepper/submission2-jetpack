package com.example.moviescatalogue.ui.favorite

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.example.moviescatalogue.data.MainRepository
import com.example.moviescatalogue.data.local.entity.FavoriteMovies
import com.example.moviescatalogue.data.local.entity.FavoriteShows
import com.example.moviescatalogue.data.local.entity.MoviesEntity
import com.example.moviescatalogue.ui.favorite.di.FavoriteScope
import com.example.moviescatalogue.utils.wrapEspressoIdlingResource
import kotlinx.coroutines.launch
import javax.inject.Inject

@FavoriteScope
class FavoriteViewModel @Inject constructor(private val mainRepository: MainRepository) :
    ViewModel() {

    private val _favoriteMovies = MutableLiveData<PagedList<FavoriteMovies>>()
    val favoriteMovies: LiveData<PagedList<FavoriteMovies>>
        get() = _favoriteMovies

    private val _favoriteShows = MutableLiveData<PagedList<FavoriteShows>>()
    val favoriteShows: LiveData<PagedList<FavoriteShows>>
        get() = _favoriteShows

    fun getFavoriteMoviesPaging() {
        wrapEspressoIdlingResource {
            viewModelScope.launch {
                val movieList = mainRepository.getMoviesFavoritePaging()
                try {
                    Log.d("TG", movieList.value.toString())
                    _favoriteMovies.value = movieList.value
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun getFavoriteShowsPaging() {
        wrapEspressoIdlingResource {
            viewModelScope.launch {
                val showsList = mainRepository.getShowsFavoritePaging()
                try {
                    Log.d("TG", showsList.value.toString())
                    _favoriteShows.postValue(showsList.value)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    suspend fun getFavoriteMoviePaging(): LiveData<PagedList<FavoriteMovies>> = mainRepository.getMoviesFavoritePaging()

    suspend fun getFavoriteShowPaging(): LiveData<PagedList<FavoriteShows>> = mainRepository.getShowsFavoritePaging()

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
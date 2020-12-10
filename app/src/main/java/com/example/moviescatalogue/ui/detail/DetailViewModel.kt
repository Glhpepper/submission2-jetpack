package com.example.moviescatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import com.example.moviescatalogue.data.MainRepository
import com.example.moviescatalogue.data.local.entity.FavoriteMovies
import com.example.moviescatalogue.data.local.entity.FavoriteShows
import com.example.moviescatalogue.data.remote.response.ResponseDetailMovies
import com.example.moviescatalogue.data.remote.response.ResponseDetailShows
import com.example.moviescatalogue.ui.detail.di.DetailScope
import com.example.moviescatalogue.utils.wrapEspressoIdlingResource
import kotlinx.coroutines.launch
import javax.inject.Inject

@DetailScope
class DetailViewModel @Inject constructor(private val mainRepository: MainRepository) :
    ViewModel() {

    private val _detailContentMovie = MutableLiveData<ResponseDetailMovies>()
    val detailContentMovie: LiveData<ResponseDetailMovies>
        get() = _detailContentMovie

    private val _detailContentShows = MutableLiveData<ResponseDetailShows>()
    val detailContentShows: LiveData<ResponseDetailShows>
        get() = _detailContentShows

    fun getDetailMovie(id: String) {
        wrapEspressoIdlingResource {
            viewModelScope.launch {
                val detailMovie = mainRepository.getDetailMovie(id)
                try {
                    _detailContentMovie.postValue(detailMovie.value)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun getDetailTvShows(id: String) {
        wrapEspressoIdlingResource {
            viewModelScope.launch {
                val detailShow = mainRepository.getDetailTvShows(id)
                try {
                    _detailContentShows.postValue(detailShow.value)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    suspend fun checkFavoriteMovies(id: Int): Boolean {
        return mainRepository.checkFavoriteMovies(id)
    }

    fun insertFavoriteMovies(favoriteMovies: FavoriteMovies) {
        viewModelScope.launch {
            mainRepository.insertFavoriteMovies(favoriteMovies)
        }
    }

    fun deleteFavoriteMoviesById(id: Int) {
        viewModelScope.launch {
            mainRepository.deleteFavoriteMoviesById(id)
        }
    }

    suspend fun checkFavoriteShows(id: Int): Boolean {
        return mainRepository.checkFavoriteShows(id)
    }

    fun insertFavoriteShows(favoriteShows: FavoriteShows) {
        viewModelScope.launch {
            mainRepository.insertFavoriteShows(favoriteShows)
        }
    }

    fun deleteFavoriteShowsById(id: Int) {
        viewModelScope.launch {
            mainRepository.deleteFavoriteShowsById(id)
        }
    }
}
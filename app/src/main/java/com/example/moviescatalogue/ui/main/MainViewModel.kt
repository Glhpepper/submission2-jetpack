package com.example.moviescatalogue.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviescatalogue.data.MainRepository
import com.example.moviescatalogue.data.local.entity.MoviesEntity
import com.example.moviescatalogue.data.local.entity.TvShowsEntity
import com.example.moviescatalogue.ui.main.di.MainScope
import com.example.moviescatalogue.utils.wrapEspressoIdlingResource
import kotlinx.coroutines.launch
import javax.inject.Inject

@MainScope
class MainViewModel @Inject constructor(private val mainRepo: MainRepository) : ViewModel() {

    private val _listMoviesApi = MutableLiveData<List<MoviesEntity>?>()
    val listMoviesApi: LiveData<List<MoviesEntity>?>
        get() = _listMoviesApi

    private val _listShowsApi = MutableLiveData<List<TvShowsEntity>?>()
    val listShowsApi: LiveData<List<TvShowsEntity>?>
        get() = _listShowsApi

    fun getMovies() {
        viewModelScope.launch {
            val resultMovies = mainRepo.getMovies()
            try {
                _listMoviesApi.value = resultMovies.value
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getTvShows() {
        wrapEspressoIdlingResource {
            viewModelScope.launch {
                val resultTvShows = mainRepo.getTvShows()
                try {
                    _listShowsApi.value = resultTvShows.value
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun getMoviesApi() {
        wrapEspressoIdlingResource {
            viewModelScope.launch {
                val resultMoviesApi = mainRepo.getMoviesApi()
                try {
                    _listMoviesApi.value = resultMoviesApi.value?.results
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun getTvShowsApi() {
        wrapEspressoIdlingResource {
            viewModelScope.launch {
                val resultTvShows = mainRepo.getShowsApi()
                try {
                    _listShowsApi.value = resultTvShows.value?.results
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}

package com.example.moviescatalogue.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviescatalogue.data.MainRepository
import com.example.moviescatalogue.data.local.entity.MoviesEntity
import com.example.moviescatalogue.data.local.entity.TvShowsEntity
import com.example.moviescatalogue.ui.main.di.MainScope
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
                _listMoviesApi.value = resultMovies
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getTvShows() {
        viewModelScope.launch {
            val resultTvShows = mainRepo.getTvShows()
            try {
                _listShowsApi.value = resultTvShows
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getMoviesApi() {
        viewModelScope.launch {
            val resultMoviesApi = mainRepo.getMoviesApi()
            try {
                _listMoviesApi.value = resultMoviesApi.results
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getTvShowsApi() {
        viewModelScope.launch {
            val resultTvShows = mainRepo.getShowsApi()
            try {
                _listShowsApi.value = resultTvShows.results
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

package com.example.moviescatalogue.ui.main

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviescatalogue.data.MainRepository
import com.example.moviescatalogue.data.local.entity.MoviesEntity
import com.example.moviescatalogue.data.local.entity.TvShowsEntity
import com.example.moviescatalogue.ui.main.di.MainScope
import com.example.moviescatalogue.utils.wrapEspressoIdlingResource
import kotlinx.coroutines.launch
import javax.inject.Inject

@MainScope
class MainViewModel @Inject constructor(private val mainRepo: MainRepository) : ViewModel() {

    private val _listMoviesApi = MutableLiveData<PagingData<MoviesEntity>>()
    val listMoviesApi: LiveData<PagingData<MoviesEntity>>
        get() = _listMoviesApi

    private val _listShowsApi = MutableLiveData<List<TvShowsEntity>?>()
    val listShowsApi: LiveData<List<TvShowsEntity>?>
        get() = _listShowsApi

    private val _listShowsApiPaging = MutableLiveData<PagingData<TvShowsEntity>?>()
    val listShowsApiPaging: LiveData<PagingData<TvShowsEntity>?>
        get() = _listShowsApiPaging

    fun getMoviesApi() {
        wrapEspressoIdlingResource {
            viewModelScope.launch {
                val resultMoviesApi = mainRepo.getMoviesApi().cachedIn(viewModelScope).asLiveData()
                try {
                    _listMoviesApi.value = resultMoviesApi.value
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    suspend fun getMovieApiPaging() =
        wrapEspressoIdlingResource {
            mainRepo.getMoviesApi().cachedIn(viewModelScope)
        }

    suspend fun getShowsApiPaging() =
        wrapEspressoIdlingResource {
            mainRepo.getShowsApi().cachedIn(viewModelScope)
        }


    fun getTvShowsApi() {
        wrapEspressoIdlingResource {
            viewModelScope.launch {
                val resultTvShows = mainRepo.getShowsApi().cachedIn(viewModelScope).asLiveData()
                try {
                    _listShowsApiPaging.value = resultTvShows.value
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}

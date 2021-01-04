package com.example.moviescatalogue.ui.main

import androidx.lifecycle.*
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviescatalogue.data.MainRepository
import com.example.moviescatalogue.data.local.entity.MoviesEntity
import com.example.moviescatalogue.data.local.entity.TvShowsEntity
import com.example.moviescatalogue.ui.main.di.MainScope
import com.example.moviescatalogue.utils.wrapEspressoIdlingResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@MainScope
class MainViewModel @Inject constructor(private val mainRepo: MainRepository) : ViewModel() {

    private lateinit var _listMoviesApi: LiveData<PagingData<MoviesEntity>>
    val listMoviesApi
        get() = _listMoviesApi

    private lateinit var _listShowsApi: LiveData<PagingData<TvShowsEntity>>
    val listShowsApi
        get() = _listShowsApi

    suspend fun getMovieApiPaging() {
        wrapEspressoIdlingResource {
            val movieList = mainRepo.getMoviesApi().cachedIn(viewModelScope)
            try {
                _listMoviesApi = movieList
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    suspend fun getShowsApiPaging(){
        wrapEspressoIdlingResource {
            val showsList = mainRepo.getShowsApi().cachedIn(viewModelScope)
            try {
                _listShowsApi = showsList
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

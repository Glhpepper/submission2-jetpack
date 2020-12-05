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

    private val _listMoviesApi = MutableLiveData<PagingData<MoviesEntity>>()
    val listMoviesApi: LiveData<PagingData<MoviesEntity>>
        get() = _listMoviesApi

    private val _listShowsApi = MutableLiveData<List<TvShowsEntity>?>()
    val listShowsApi: LiveData<List<TvShowsEntity>?>
        get() = _listShowsApi

    suspend fun getMovieApiPaging(): Flow<PagingData<MoviesEntity>> {
        wrapEspressoIdlingResource {
            return mainRepo.getMoviesApi().cachedIn(viewModelScope)
        }
    }

    suspend fun getShowsApiPaging(): Flow<PagingData<TvShowsEntity>> {
        wrapEspressoIdlingResource {
           return mainRepo.getShowsApi().cachedIn(viewModelScope)
        }
    }
}

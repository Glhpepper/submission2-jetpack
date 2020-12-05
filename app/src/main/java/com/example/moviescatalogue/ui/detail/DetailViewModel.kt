package com.example.moviescatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import com.example.moviescatalogue.data.MainRepository
import com.example.moviescatalogue.data.remote.response.ResponseDetailMovies
import com.example.moviescatalogue.data.remote.response.ResponseDetailShows
import com.example.moviescatalogue.ui.detail.di.DetailScope
import com.example.moviescatalogue.utils.wrapEspressoIdlingResource
import kotlinx.coroutines.launch
import javax.inject.Inject

@DetailScope
class DetailViewModel @Inject constructor(private val mainRepo: MainRepository) : ViewModel() {

    private val _detailContentMovie = MutableLiveData<ResponseDetailMovies>()
    val detailContentMovie: LiveData<ResponseDetailMovies>
        get() = _detailContentMovie

    private val _detailContentShows = MutableLiveData<ResponseDetailShows>()
    val detailContentShows: LiveData<ResponseDetailShows>
        get() = _detailContentShows

    fun getDetailMovie(id: String) {
        wrapEspressoIdlingResource {
            viewModelScope.launch {
                val detailMovie = mainRepo.getDetailMovie(id)
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
                val detailShow = mainRepo.getDetailTvShows(id)
                try {
                    _detailContentShows.postValue(detailShow.value)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}
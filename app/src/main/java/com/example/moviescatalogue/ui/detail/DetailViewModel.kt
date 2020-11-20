package com.example.moviescatalogue.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviescatalogue.data.MainRepository
import com.example.moviescatalogue.data.local.entity.DetailEntity
import com.example.moviescatalogue.data.local.entity.DetailTopCastEntity
import com.example.moviescatalogue.data.remote.response.ResponseDetailMovies
import com.example.moviescatalogue.data.remote.response.ResponseDetailShows
import com.example.moviescatalogue.ui.detail.di.DetailScope
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

    private val _detailContentOffline = MutableLiveData<DetailEntity?>()
    val detailContentOffline: LiveData<DetailEntity?>
        get() = _detailContentOffline

    private val _detailContentTopCast = MutableLiveData<List<DetailTopCastEntity>?>()
    val detailContentTopCast: LiveData<List<DetailTopCastEntity>?>
        get() = _detailContentTopCast

    fun getDetailMovie(id: String) {
        viewModelScope.launch {
            val detailMovie = mainRepo.getDetailMovie(id)
            try {
                _detailContentMovie.value = detailMovie
                Log.d("TG", detailMovie.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getDetailTvShows(id: String) {
        viewModelScope.launch {
            val detailShow = mainRepo.getDetailTvShows(id)
            try {
                _detailContentShows.value = detailShow
                Log.d("TG", detailShow.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getDetailOffline(id: String) {
        viewModelScope.launch {
            val detail = mainRepo.getDetailOffline(id)
            try {
                _detailContentTopCast.value = detail[0].detailTopCast
                _detailContentOffline.value = detail[0]
                Log.d("TG", detail.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}
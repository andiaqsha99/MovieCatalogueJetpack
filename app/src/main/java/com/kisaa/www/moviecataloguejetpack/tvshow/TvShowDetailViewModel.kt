package com.kisaa.www.moviecataloguejetpack.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kisaa.www.moviecataloguejetpack.data.MovieRepository
import com.kisaa.www.moviecataloguejetpack.data.source.local.FavoriteEntity
import com.kisaa.www.moviecataloguejetpack.data.source.remote.TvShowEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TvShowDetailViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private lateinit var dataTvShow: LiveData<TvShowEntity>
    private lateinit var favTvShow: LiveData<FavoriteEntity>

    fun getTvShowDetail(tvShowId: String?): LiveData<TvShowEntity> {
        dataTvShow = movieRepository.getDetailTvShow(tvShowId)
        return dataTvShow
    }

    fun insertToFavorite(favorite: FavoriteEntity) = viewModelScope.launch(Dispatchers.IO) {
        movieRepository.insertToFavorite(favorite)
    }

    fun deleteFromFavorite(favorite: FavoriteEntity) = viewModelScope.launch(Dispatchers.IO) {
        movieRepository.deleteFromFavorite(favorite)
    }

    fun checkFavoriteById(id: String): LiveData<FavoriteEntity> {
        favTvShow = movieRepository.checkFavoriteById(id)
        return favTvShow
    }
}
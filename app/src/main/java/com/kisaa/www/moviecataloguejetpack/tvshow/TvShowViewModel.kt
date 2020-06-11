package com.kisaa.www.moviecataloguejetpack.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kisaa.www.moviecataloguejetpack.data.MovieRepository
import com.kisaa.www.moviecataloguejetpack.data.source.remote.TvShowEntity

class TvShowViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    private lateinit var listTvShow: LiveData<List<TvShowEntity>>

    fun getListTvShows(): LiveData<List<TvShowEntity>> {
        listTvShow = movieRepository.getListTvShows()
        return listTvShow
    }
}

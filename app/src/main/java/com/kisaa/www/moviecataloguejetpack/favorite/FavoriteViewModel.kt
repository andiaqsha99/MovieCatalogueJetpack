package com.kisaa.www.moviecataloguejetpack.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.kisaa.www.moviecataloguejetpack.data.MovieRepository
import com.kisaa.www.moviecataloguejetpack.data.source.local.FavoriteEntity

class FavoriteViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private lateinit var listMovies: LiveData<PagedList<FavoriteEntity>>
    private lateinit var listTvShows: LiveData<PagedList<FavoriteEntity>>

    fun getFavoriteMovies(): LiveData<PagedList<FavoriteEntity>> {
        listMovies = movieRepository.getFavoriteMovies()
        return listMovies
    }

    fun getFavoriteTvShows(): LiveData<PagedList<FavoriteEntity>> {
        listTvShows = movieRepository.getFavoriteTvShows()
        return listTvShows
    }
}
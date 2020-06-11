package com.kisaa.www.moviecataloguejetpack.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kisaa.www.moviecataloguejetpack.data.MovieRepository
import com.kisaa.www.moviecataloguejetpack.data.source.remote.MovieEntity

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private lateinit var listMovies: LiveData<List<MovieEntity>>

    fun getListMovies(): LiveData<List<MovieEntity>> {
        listMovies = movieRepository.getListMovies()
        return listMovies
    }
}

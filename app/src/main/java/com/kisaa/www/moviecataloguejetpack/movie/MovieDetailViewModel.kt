package com.kisaa.www.moviecataloguejetpack.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kisaa.www.moviecataloguejetpack.data.MovieRepository
import com.kisaa.www.moviecataloguejetpack.data.source.local.FavoriteEntity
import com.kisaa.www.moviecataloguejetpack.data.source.remote.MovieEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private lateinit var dataMovie: LiveData<MovieEntity>
    private lateinit var favMovie: LiveData<FavoriteEntity>

    fun getDetailMovie(movieId: String?): LiveData<MovieEntity> {
        dataMovie = movieRepository.getDetailMovie(movieId)
        return dataMovie
    }

    fun insertToFavorite(favorite: FavoriteEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            movieRepository.insertToFavorite(favorite)
        }

    fun deleteFromFavorite(favorite: FavoriteEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            movieRepository.deleteFromFavorite(favorite)
        }

    fun checkFavoriteById(id: String): LiveData<FavoriteEntity> {
        favMovie = movieRepository.checkFavoriteById(id)
        return favMovie
    }
}
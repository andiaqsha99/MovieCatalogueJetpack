package com.kisaa.www.moviecatalogue.favorite.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kisaa.www.moviecataloguejetpack.core.domain.model.Favorite
import com.kisaa.www.moviecataloguejetpack.core.domain.usecase.MovieUseCase

class FavoriteViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    val listMovies = movieUseCase.getListFavoriteMovie().asLiveData()
    val listTvShows = movieUseCase.getListFavoriteTvShow().asLiveData()

    private lateinit var favMovie: LiveData<Favorite?>

    fun insertToFavorite(favorite: Favorite) =
        movieUseCase.addToFavorite(favorite)

    fun deleteFromFavorite(favorite: Favorite) =
        movieUseCase.deleteFromFavorite(favorite)

    fun checkFavoriteById(id: String): LiveData<Favorite?> {
        favMovie = movieUseCase.checkFavoriteById(id).asLiveData()
        return favMovie
    }
}
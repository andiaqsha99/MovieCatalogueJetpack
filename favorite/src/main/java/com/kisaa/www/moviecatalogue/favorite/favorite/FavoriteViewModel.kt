package com.kisaa.www.moviecatalogue.favorite.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kisaa.www.moviecataloguejetpack.core.domain.usecase.MovieUseCase

class FavoriteViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val listMovies = movieUseCase.getListFavoriteMovie().asLiveData()
    val listTvShows = movieUseCase.getListFavoriteTvShow().asLiveData()
}
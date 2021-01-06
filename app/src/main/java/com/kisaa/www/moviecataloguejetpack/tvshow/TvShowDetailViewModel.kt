package com.kisaa.www.moviecataloguejetpack.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kisaa.www.moviecataloguejetpack.core.domain.model.Favorite
import com.kisaa.www.moviecataloguejetpack.core.domain.usecase.MovieUseCase

class TvShowDetailViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    private lateinit var favTvShow: LiveData<Favorite?>

    fun insertToFavorite(favorite: Favorite) = movieUseCase.addToFavorite(favorite)

    fun deleteFromFavorite(favorite: Favorite) = movieUseCase.deleteFromFavorite(favorite)

    fun checkFavoriteById(id: String): LiveData<Favorite?> {
        favTvShow = movieUseCase.checkFavoriteById(id).asLiveData()
        return favTvShow
    }
}
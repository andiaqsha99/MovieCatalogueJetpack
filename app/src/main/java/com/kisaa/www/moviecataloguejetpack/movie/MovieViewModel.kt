package com.kisaa.www.moviecataloguejetpack.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kisaa.www.moviecataloguejetpack.core.domain.usecase.MovieUseCase

class MovieViewModel(movieUseCase: MovieUseCase) : ViewModel() {

    val listMovies = movieUseCase.getListMovie().asLiveData()
}

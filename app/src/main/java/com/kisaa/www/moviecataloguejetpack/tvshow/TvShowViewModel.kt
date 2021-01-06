package com.kisaa.www.moviecataloguejetpack.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kisaa.www.moviecataloguejetpack.core.domain.usecase.MovieUseCase

class TvShowViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val listTvShow = movieUseCase.getListTvShow().asLiveData()
}

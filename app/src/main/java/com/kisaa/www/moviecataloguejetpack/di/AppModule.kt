package com.kisaa.www.moviecataloguejetpack.di

import com.kisaa.www.moviecataloguejetpack.core.domain.usecase.MovieInteractor
import com.kisaa.www.moviecataloguejetpack.core.domain.usecase.MovieUseCase
import com.kisaa.www.moviecataloguejetpack.movie.MovieDetailViewModel
import com.kisaa.www.moviecataloguejetpack.movie.MovieViewModel
import com.kisaa.www.moviecataloguejetpack.tvshow.TvShowDetailViewModel
import com.kisaa.www.moviecataloguejetpack.tvshow.TvShowViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { MovieDetailViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
    viewModel { TvShowDetailViewModel(get()) }
}
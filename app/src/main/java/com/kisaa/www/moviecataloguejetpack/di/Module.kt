package com.kisaa.www.moviecataloguejetpack.di

import androidx.room.Room
import com.kisaa.www.moviecataloguejetpack.data.MovieRepository
import com.kisaa.www.moviecataloguejetpack.data.source.local.FavoriteDatabase
import com.kisaa.www.moviecataloguejetpack.favorite.FavoriteViewModel
import com.kisaa.www.moviecataloguejetpack.movie.MovieDetailViewModel
import com.kisaa.www.moviecataloguejetpack.movie.MovieViewModel
import com.kisaa.www.moviecataloguejetpack.network.NetworkConfig
import com.kisaa.www.moviecataloguejetpack.tvshow.TvShowDetailViewModel
import com.kisaa.www.moviecataloguejetpack.tvshow.TvShowViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        Room.databaseBuilder(androidApplication(), FavoriteDatabase::class.java, "Favorite_db")
            .build()
    }

    single {
        get<FavoriteDatabase>().favoriteDao()
    }

    single {
        NetworkConfig.api()
    }

    single {
        MovieRepository(get(), get())
    }

    viewModel {
        MovieViewModel(get())
    }

    viewModel {
        TvShowViewModel(get())
    }

    viewModel {
        MovieDetailViewModel(get())
    }

    viewModel {
        TvShowDetailViewModel(get())
    }

    viewModel {
        FavoriteViewModel(get())
    }
}
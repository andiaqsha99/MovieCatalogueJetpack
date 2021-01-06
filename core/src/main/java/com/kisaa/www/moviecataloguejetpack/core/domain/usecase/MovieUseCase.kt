package com.kisaa.www.moviecataloguejetpack.core.domain.usecase

import com.kisaa.www.moviecataloguejetpack.core.data.Resource
import com.kisaa.www.moviecataloguejetpack.core.domain.model.Favorite
import com.kisaa.www.moviecataloguejetpack.core.domain.model.Movie
import com.kisaa.www.moviecataloguejetpack.core.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getListMovie(): Flow<Resource<List<Movie>>>
    fun getListTvShow(): Flow<Resource<List<TvShow>>>
    fun getListFavoriteMovie(): Flow<List<Favorite>>
    fun getListFavoriteTvShow(): Flow<List<Favorite>>
    fun addToFavorite(favorite: Favorite)
    fun deleteFromFavorite(favorite: Favorite)
    fun checkFavoriteById(id: String): Flow<Favorite?>
}
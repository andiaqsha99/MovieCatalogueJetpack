package com.kisaa.www.moviecataloguejetpack.core.domain.usecase

import com.kisaa.www.moviecataloguejetpack.core.data.Resource
import com.kisaa.www.moviecataloguejetpack.core.domain.model.Favorite
import com.kisaa.www.moviecataloguejetpack.core.domain.model.Movie
import com.kisaa.www.moviecataloguejetpack.core.domain.model.TvShow
import com.kisaa.www.moviecataloguejetpack.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val movieRepository: IMovieRepository) : MovieUseCase {
    override fun getListMovie(): Flow<Resource<List<Movie>>> = movieRepository.getListMovie()

    override fun getListTvShow(): Flow<Resource<List<TvShow>>> = movieRepository.getListTvShow()

    override fun getListFavoriteMovie(): Flow<List<Favorite>> =
        movieRepository.getListFavoriteMovie()

    override fun getListFavoriteTvShow(): Flow<List<Favorite>> =
        movieRepository.getListFavoriteTvShow()

    override fun addToFavorite(favorite: Favorite) = movieRepository.addToFavorite(favorite)

    override fun deleteFromFavorite(favorite: Favorite) =
        movieRepository.deleteFromFavorite(favorite)

    override fun checkFavoriteById(id: String): Flow<Favorite?> =
        movieRepository.checkFavoriteById(id)

}
package com.kisaa.www.moviecataloguejetpack.core.data.source.local

import com.kisaa.www.moviecataloguejetpack.core.data.source.local.entity.FavoriteEntity
import com.kisaa.www.moviecataloguejetpack.core.data.source.local.entity.MovieEntity
import com.kisaa.www.moviecataloguejetpack.core.data.source.local.entity.TvShowEntity
import com.kisaa.www.moviecataloguejetpack.core.data.source.local.room.FavoriteDao
import com.kisaa.www.moviecataloguejetpack.core.data.source.local.room.MovieDao
import com.kisaa.www.moviecataloguejetpack.core.data.source.local.room.TvShowDao
import kotlinx.coroutines.flow.Flow

class LocalDataResource(
    private val favoriteDao: FavoriteDao,
    private val movieDao: MovieDao,
    private val tvShowDao: TvShowDao
) {

    fun getListFavoriteMovie(): Flow<List<FavoriteEntity>> = favoriteDao.getListFavoriteMovie()

    fun getListFavoriteTvShow(): Flow<List<FavoriteEntity>> = favoriteDao.getListFavoriteTvShow()

    suspend fun addToFavorite(favoriteEntity: FavoriteEntity) =
        favoriteDao.addToFavorite(favoriteEntity)

    suspend fun deleteFromFavorite(favoriteEntity: FavoriteEntity) =
        favoriteDao.deleteFromFavorite(favoriteEntity)

    fun checkFavoriteById(id: String): Flow<FavoriteEntity?> = favoriteDao.checkFavoriteById(id)

    fun getListMovie(): Flow<List<MovieEntity>> = movieDao.getListMovie()

    suspend fun insertToMovie(movie: List<MovieEntity>) = movieDao.insertToMovie(movie)

    fun getListTvShow(): Flow<List<TvShowEntity>> = tvShowDao.getListTvShow()

    suspend fun insertToTvShow(tvShow: List<TvShowEntity>) = tvShowDao.insertToTvShow(tvShow)
}
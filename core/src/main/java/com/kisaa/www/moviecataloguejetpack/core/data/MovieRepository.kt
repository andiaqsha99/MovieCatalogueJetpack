package com.kisaa.www.moviecataloguejetpack.core.data

import com.kisaa.www.moviecataloguejetpack.core.data.source.local.LocalDataResource
import com.kisaa.www.moviecataloguejetpack.core.data.source.remote.RemoteDataSource
import com.kisaa.www.moviecataloguejetpack.core.data.source.remote.network.ApiResponse
import com.kisaa.www.moviecataloguejetpack.core.data.source.remote.response.MovieResponse
import com.kisaa.www.moviecataloguejetpack.core.data.source.remote.response.TvShowResponse
import com.kisaa.www.moviecataloguejetpack.core.domain.model.Favorite
import com.kisaa.www.moviecataloguejetpack.core.domain.model.Movie
import com.kisaa.www.moviecataloguejetpack.core.domain.model.TvShow
import com.kisaa.www.moviecataloguejetpack.core.domain.repository.IMovieRepository
import com.kisaa.www.moviecataloguejetpack.core.utils.DataMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MovieRepository(
    private val localDataResource: LocalDataResource,
    private val remoteDataSource: RemoteDataSource
) : IMovieRepository {
    override fun getListMovie(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataResource.getListMovie().map {
                    DataMapper.mapMovieEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getListMovies()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapMovieResponseToEntity(data)
                localDataResource.insertToMovie(movieList)
            }

        }.asFlow()

    override fun getListTvShow(): Flow<Resource<List<TvShow>>> =
        object : NetworkBoundResource<List<TvShow>, List<TvShowResponse>>() {
            override fun loadFromDB(): Flow<List<TvShow>> {
                return localDataResource.getListTvShow().map {
                    DataMapper.mapTvShowEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: List<TvShow>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getListTvShow()

            override suspend fun saveCallResult(data: List<TvShowResponse>) {
                val tvShowList = DataMapper.mapTvShowResponseToEntity(data)
                localDataResource.insertToTvShow(tvShowList)
            }

        }.asFlow()

    override fun getListFavoriteMovie(): Flow<List<Favorite>> {
        return localDataResource.getListFavoriteMovie().map {
            DataMapper.mapListFavoriteEntityToDomain(it)
        }
    }

    override fun getListFavoriteTvShow(): Flow<List<Favorite>> {
        return localDataResource.getListFavoriteTvShow().map {
            DataMapper.mapListFavoriteEntityToDomain(it)
        }
    }

    override fun addToFavorite(favorite: Favorite) {
        val favoriteEntity = DataMapper.mapFavoriteDomainToEntity(favorite)
        CoroutineScope(Dispatchers.IO).launch {
            localDataResource.addToFavorite(favoriteEntity)
        }
    }

    override fun deleteFromFavorite(favorite: Favorite) {
        val favoriteEntity = DataMapper.mapFavoriteDomainToEntity(favorite)
        CoroutineScope(Dispatchers.IO).launch {
            localDataResource.deleteFromFavorite(favoriteEntity)
        }
    }

    override fun checkFavoriteById(id: String): Flow<Favorite?> {
        return localDataResource.checkFavoriteById(id).map {
            DataMapper.mapFavoriteEntityToDomain(it)
        }
    }

}

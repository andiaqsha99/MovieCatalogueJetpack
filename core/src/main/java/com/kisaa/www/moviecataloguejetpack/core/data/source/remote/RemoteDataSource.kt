package com.kisaa.www.moviecataloguejetpack.core.data.source.remote

import com.kisaa.www.moviecataloguejetpack.core.data.source.remote.network.ApiResponse
import com.kisaa.www.moviecataloguejetpack.core.data.source.remote.network.ApiService
import com.kisaa.www.moviecataloguejetpack.core.data.source.remote.response.MovieResponse
import com.kisaa.www.moviecataloguejetpack.core.data.source.remote.response.TvShowResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getListMovies(): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getListMovies()
                val listMovie = response.results
                if (listMovie.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getListTvShow(): Flow<ApiResponse<List<TvShowResponse>>> {
        return flow {
            try {
                val response = apiService.getListTvShows()
                val listTvShow = response.results
                if (listTvShow.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}
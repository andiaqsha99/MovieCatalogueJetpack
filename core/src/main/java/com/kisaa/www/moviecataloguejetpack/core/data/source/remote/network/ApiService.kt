package com.kisaa.www.moviecataloguejetpack.core.data.source.remote.network

import com.kisaa.www.moviecataloguejetpack.core.BuildConfig
import com.kisaa.www.moviecataloguejetpack.core.data.source.remote.response.ListMovieResponse
import com.kisaa.www.moviecataloguejetpack.core.data.source.remote.response.ListTvShowResponse
import retrofit2.http.GET

interface ApiService {
    @GET("discover/movie?api_key=${BuildConfig.TMDB_API_KEY}&language=en-US")
    suspend fun getListMovies(): ListMovieResponse

    @GET("discover/tv?api_key=${BuildConfig.TMDB_API_KEY}&language=en-US")
    suspend fun getListTvShows(): ListTvShowResponse
}
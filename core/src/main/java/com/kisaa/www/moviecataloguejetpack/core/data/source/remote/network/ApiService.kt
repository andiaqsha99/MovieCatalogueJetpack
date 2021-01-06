package com.kisaa.www.moviecataloguejetpack.core.data.source.remote.network

import com.kisaa.www.moviecataloguejetpack.core.BuildConfig
import com.kisaa.www.moviecataloguejetpack.core.data.source.remote.response.ListMovieResponse
import com.kisaa.www.moviecataloguejetpack.core.data.source.remote.response.ListTvShowResponse
import com.kisaa.www.moviecataloguejetpack.core.data.source.remote.response.MovieResponse
import com.kisaa.www.moviecataloguejetpack.core.data.source.remote.response.TvShowResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("discover/movie?api_key=${BuildConfig.TMDB_API_KEY}&language=en-US")
    suspend fun getListMovies(): ListMovieResponse

    @GET("discover/tv?api_key=${BuildConfig.TMDB_API_KEY}&language=en-US")
    suspend fun getListTvShows(): ListTvShowResponse

    @GET("movie/{id}?api_key=${BuildConfig.TMDB_API_KEY}&language=en-US")
    suspend fun getDetailMovie(
        @Path("id") id: String?
    ): MovieResponse

    @GET("tv/{id}?api_key=${BuildConfig.TMDB_API_KEY}&language=en-US")
    suspend fun getDetailTvShow(
        @Path("id") id: String?
    ): TvShowResponse
}
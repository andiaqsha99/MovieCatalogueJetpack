package com.kisaa.www.moviecataloguejetpack.network

import com.kisaa.www.moviecataloguejetpack.BuildConfig
import com.kisaa.www.moviecataloguejetpack.data.source.remote.MovieEntity
import com.kisaa.www.moviecataloguejetpack.data.source.remote.MoviesResponse
import com.kisaa.www.moviecataloguejetpack.data.source.remote.TvShowEntity
import com.kisaa.www.moviecataloguejetpack.data.source.remote.TvShowsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("discover/movie?api_key=${BuildConfig.TMDB_API_KEY}&language=en-US")
    fun getMovies(): Observable<MoviesResponse>

    @GET("discover/tv?api_key=${BuildConfig.TMDB_API_KEY}&language=en-US")
    fun getTvShows(): Observable<TvShowsResponse>

    @GET("movie/{id}?api_key=${BuildConfig.TMDB_API_KEY}&language=en-US")
    fun getDetailMovie(
        @Path("id") id: String?
    ): Observable<MovieEntity>

    @GET("tv/{id}?api_key=${BuildConfig.TMDB_API_KEY}&language=en-US")
    fun getDetailTvShow(
        @Path("id") id: String?
    ): Observable<TvShowEntity>
}
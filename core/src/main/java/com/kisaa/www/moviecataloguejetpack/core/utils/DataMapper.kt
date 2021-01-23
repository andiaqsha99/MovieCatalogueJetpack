package com.kisaa.www.moviecataloguejetpack.core.utils

import com.kisaa.www.moviecataloguejetpack.core.data.source.local.entity.FavoriteEntity
import com.kisaa.www.moviecataloguejetpack.core.data.source.local.entity.MovieEntity
import com.kisaa.www.moviecataloguejetpack.core.data.source.local.entity.TvShowEntity
import com.kisaa.www.moviecataloguejetpack.core.data.source.remote.response.MovieResponse
import com.kisaa.www.moviecataloguejetpack.core.data.source.remote.response.TvShowResponse
import com.kisaa.www.moviecataloguejetpack.core.domain.model.Favorite
import com.kisaa.www.moviecataloguejetpack.core.domain.model.Movie
import com.kisaa.www.moviecataloguejetpack.core.domain.model.TvShow

object DataMapper {

    fun mapMovieEntityToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                title = it.title,
                overview = it.overview,
                backdrop_path = it.backdropPath,
                poster_path = it.posterPath,
                vote_average = it.voteAverage
            )
        }

    fun mapMovieResponseToEntity(input: List<MovieResponse>): List<MovieEntity> =
        input.map {
            MovieEntity(
                id = it.id,
                title = it.title,
                overview = it.overview,
                voteAverage = it.vote_average,
                posterPath = it.poster_path,
                backdropPath = it.backdrop_path
            )
        }

    fun mapTvShowEntityToDomain(input: List<TvShowEntity>): List<TvShow> =
        input.map {
            TvShow(
                id = it.id,
                name = it.name,
                overview = it.overview,
                backdrop_path = it.backdropPath,
                poster_path = it.posterPath,
                vote_average = it.voteAverage
            )
        }

    fun mapTvShowResponseToEntity(input: List<TvShowResponse>): List<TvShowEntity> =
        input.map {
            TvShowEntity(
                id = it.id,
                name = it.name,
                overview = it.overview,
                voteAverage = it.vote_average,
                posterPath = it.poster_path,
                backdropPath = it.backdrop_path
            )
        }

    fun mapListFavoriteEntityToDomain(input: List<FavoriteEntity>): List<Favorite> =
        input.map {
            Favorite(
                id = it.id,
                title = it.title,
                overview = it.overview,
                backdropPath = it.backdropPath,
                posterPath = it.posterPath,
                voteAverage = it.voteAverage,
                category = it.category
            )
        }

    fun mapFavoriteEntityToDomain(input: FavoriteEntity?): Favorite? =
        input?.let {
            Favorite(
                id = input.id,
                title = input.title,
                overview = input.overview,
                backdropPath = input.backdropPath,
                posterPath = input.posterPath,
                voteAverage = input.voteAverage,
                category = input.category
            )
        }


    fun mapFavoriteDomainToEntity(input: Favorite): FavoriteEntity =
        FavoriteEntity(
            id = input.id,
            title = input.title,
            overview = input.overview,
            backdropPath = input.backdropPath,
            posterPath = input.posterPath,
            voteAverage = input.voteAverage,
            category = input.category
        )

}
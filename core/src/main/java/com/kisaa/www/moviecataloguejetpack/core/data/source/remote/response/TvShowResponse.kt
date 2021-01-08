package com.kisaa.www.moviecataloguejetpack.core.data.source.remote.response

data class TvShowResponse(
    val id: String,
    val poster_path: String?,
    val name: String,
    val overview: String?,
    val vote_average: String?,
    val backdrop_path: String?
)
package com.kisaa.www.moviecataloguejetpack.data.source.remote

data class MovieEntity(
    var id: String? = null,
    var poster_path: String? = null,
    var title: String? = null,
    var overview: String? = null,
    var vote_average: String? = null,
    var backdrop_path: String? = null
)
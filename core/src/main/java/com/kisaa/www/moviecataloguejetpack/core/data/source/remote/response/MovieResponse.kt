package com.kisaa.www.moviecataloguejetpack.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("poster_path")
    val poster_path: String?,
    @SerializedName("title")
    val title: String,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("vote_average")
    val vote_average: String?,
    @SerializedName("backdrop_path")
    val backdrop_path: String?
)
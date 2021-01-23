package com.kisaa.www.moviecataloguejetpack.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Favorite(
    val id: String,
    val title: String,
    val overview: String?,
    val voteAverage: String?,
    val posterPath: String?,
    val backdropPath: String?,
    val category: String
) : Parcelable
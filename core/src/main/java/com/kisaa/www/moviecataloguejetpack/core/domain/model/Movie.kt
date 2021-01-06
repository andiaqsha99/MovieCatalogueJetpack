package com.kisaa.www.moviecataloguejetpack.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: String,
    val poster_path: String,
    val title: String,
    val overview: String,
    val vote_average: String,
    val backdrop_path: String
) : Parcelable
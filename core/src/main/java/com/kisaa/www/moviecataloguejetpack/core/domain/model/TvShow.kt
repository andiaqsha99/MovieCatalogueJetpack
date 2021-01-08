package com.kisaa.www.moviecataloguejetpack.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShow(
    val id: String,
    val poster_path: String?,
    val name: String,
    val overview: String?,
    val vote_average: String?,
    val backdrop_path: String?
) : Parcelable
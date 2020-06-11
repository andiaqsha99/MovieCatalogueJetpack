package com.kisaa.www.moviecataloguejetpack.utils

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun ImageView.loadPoster(path: String?) {
    Glide.with(this.context)
        .load("https://image.tmdb.org/t/p/w185$path")
        .into(this)
}

fun ImageView.loadBackdrop(path: String?) {
    Glide.with(this.context)
        .load("https://image.tmdb.org/t/p/w342$path")
        .into(this)
}
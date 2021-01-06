package com.kisaa.www.moviecataloguejetpack.core.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
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

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
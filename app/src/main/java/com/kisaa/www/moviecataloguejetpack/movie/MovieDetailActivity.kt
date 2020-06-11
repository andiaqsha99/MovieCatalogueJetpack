package com.kisaa.www.moviecataloguejetpack.movie

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.google.android.material.appbar.AppBarLayout
import com.kisaa.www.moviecataloguejetpack.R
import com.kisaa.www.moviecataloguejetpack.data.source.local.FavoriteEntity
import com.kisaa.www.moviecataloguejetpack.data.source.remote.MovieEntity
import com.kisaa.www.moviecataloguejetpack.utils.*
import kotlinx.android.synthetic.main.activity_movie_detail.*
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.abs

class MovieDetailActivity : AppCompatActivity() {

    private val viewModel by viewModel<MovieDetailViewModel>()
    private var isFavorite: Boolean = false
    private var menuItem: Menu? = null
    private lateinit var favorite: FavoriteEntity

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        setSupportActionBar(toolbar)

        val movieId = intent.getStringExtra(EXTRA_ID)

        EspressoIdlingResource.increment()
        detail_progress_bar.visible()
        viewModel.checkFavoriteById(movieId!!).observe(this, Observer {
            isFavorite = it != null
        })
        viewModel.getDetailMovie(movieId).observe(this, Observer {
            populateView(it)
            movieToFavoriteEntity(it)
            detail_progress_bar.invisible()
            EspressoIdlingResource.decrement()
        })

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun populateView(movie: MovieEntity) {
        tv_movie_title.text = movie.title
        tv_movie_overview.text = movie.overview
        grade_movie.text = movie.vote_average
        img_movie.loadPoster(movie.poster_path)
        img_backdrop.loadBackdrop(movie.backdrop_path)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        appbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            title = if (abs(verticalOffset) - appBarLayout.totalScrollRange == 0) {
                movie.title
            } else {
                null
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        menuItem = menu
        setFavoriteIcon()
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorite_icon -> {
                if (isFavorite) {
                    viewModel.deleteFromFavorite(favorite)
                    toast("Delete from favorite")
                } else {
                    viewModel.insertToFavorite(favorite)
                    toast("Added to favorite")
                }
                isFavorite = !isFavorite
                setFavoriteIcon()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFavoriteIcon() {
        if (isFavorite) {
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_favorite_white_24dp)
        } else {
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_white_24dp)
        }
    }

    private fun movieToFavoriteEntity(movie: MovieEntity) {
        favorite = FavoriteEntity(
            movie.id!!,
            movie.title,
            movie.overview,
            movie.vote_average,
            movie.poster_path,
            "movie"
        )
    }
}

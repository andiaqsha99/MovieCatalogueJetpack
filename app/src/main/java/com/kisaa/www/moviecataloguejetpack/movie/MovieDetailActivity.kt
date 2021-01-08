package com.kisaa.www.moviecataloguejetpack.movie

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.appbar.AppBarLayout
import com.kisaa.www.moviecataloguejetpack.R
import com.kisaa.www.moviecataloguejetpack.core.domain.model.Favorite
import com.kisaa.www.moviecataloguejetpack.core.domain.model.Movie
import com.kisaa.www.moviecataloguejetpack.core.utils.*
import kotlinx.android.synthetic.main.activity_movie_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.abs

class MovieDetailActivity : AppCompatActivity() {

    private val viewModel by viewModel<MovieDetailViewModel>()
    private var isFavorite: Boolean = false
    private var menuItem: Menu? = null
    private lateinit var favorite: Favorite

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        setSupportActionBar(toolbar)

        val movie = intent.getParcelableExtra<Movie>(EXTRA_DATA)

        detail_progress_bar.visible()
        movie?.let {
            viewModel.checkFavoriteById(movie.id).observe(this, { data ->
                isFavorite = data != null
            })

            populateView(movie)
            movieToFavorite(movie)
            detail_progress_bar.invisible()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun populateView(movie: Movie) {
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

    private fun movieToFavorite(movie: Movie) {
        favorite = Favorite(
            movie.id,
            movie.title,
            movie.overview,
            movie.vote_average,
            movie.poster_path,
            movie.backdrop_path,
            "movie"
        )
    }
}

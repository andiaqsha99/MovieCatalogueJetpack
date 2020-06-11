package com.kisaa.www.moviecataloguejetpack.tvshow

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.google.android.material.appbar.AppBarLayout
import com.kisaa.www.moviecataloguejetpack.R
import com.kisaa.www.moviecataloguejetpack.data.source.local.FavoriteEntity
import com.kisaa.www.moviecataloguejetpack.data.source.remote.TvShowEntity
import com.kisaa.www.moviecataloguejetpack.utils.*
import kotlinx.android.synthetic.main.activity_tv_show_detail.*
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.abs

class TvShowDetailActivity : AppCompatActivity() {

    private val viewModel by viewModel<TvShowDetailViewModel>()
    private var isFavorite: Boolean = false
    private var menuItem: Menu? = null
    private lateinit var favorite: FavoriteEntity

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_show_detail)
        setSupportActionBar(toolbar)

        val tvShowId = intent.getStringExtra(EXTRA_ID)

        EspressoIdlingResource.increment()
        detail_progress_bar.visible()
        viewModel.checkFavoriteById(tvShowId!!).observe(this, Observer {
            isFavorite = it != null
        })
        viewModel.getTvShowDetail(tvShowId).observe(this, Observer {
            populateView(it)
            tvShowToFavoriteEntity(it)
            detail_progress_bar.invisible()
            EspressoIdlingResource.decrement()
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun populateView(tvShow: TvShowEntity) {
        tv_tvshow_title.text = tvShow.name
        tv_tvshow_overview.text = tvShow.overview
        grade_tv_show.text = tvShow.vote_average
        img_tvshow.loadPoster(tvShow.poster_path)
        img_backdrop.loadBackdrop(tvShow.backdrop_path)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        appbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            title = if (abs(verticalOffset) - appBarLayout.totalScrollRange == 0) {
                tvShow.name
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

    private fun tvShowToFavoriteEntity(tvShow: TvShowEntity) {
        favorite = FavoriteEntity(
            tvShow.id!!,
            tvShow.name,
            tvShow.overview,
            tvShow.vote_average,
            tvShow.poster_path,
            "tvShow"
        )
    }
}

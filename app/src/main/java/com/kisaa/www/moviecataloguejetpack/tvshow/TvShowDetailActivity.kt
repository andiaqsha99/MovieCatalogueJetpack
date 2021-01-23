package com.kisaa.www.moviecataloguejetpack.tvshow

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.navArgs
import com.google.android.material.appbar.AppBarLayout
import com.kisaa.www.moviecataloguejetpack.R
import com.kisaa.www.moviecataloguejetpack.core.domain.model.Favorite
import com.kisaa.www.moviecataloguejetpack.core.domain.model.TvShow
import com.kisaa.www.moviecataloguejetpack.core.utils.*
import com.kisaa.www.moviecataloguejetpack.databinding.ActivityTvShowDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.abs

class TvShowDetailActivity : AppCompatActivity() {

    private val viewModel by viewModel<TvShowDetailViewModel>()
    private var isFavorite: Boolean = false
    private var menuItem: Menu? = null
    private lateinit var favorite: Favorite
    private lateinit var binding: ActivityTvShowDetailBinding
    private val args: TvShowDetailActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTvShowDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val tvShow = args.tvshowItem

        binding.detailProgressBar.visible()
        tvShow.let {
            viewModel.checkFavoriteById(tvShow.id).observe(this, { data ->
                isFavorite = data != null
            })

            populateView(it)
            tvShowToFavorite(it)
            binding.detailProgressBar.invisible()
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun populateView(tvShow: TvShow) {
        binding.tvTvshowTitle.text = tvShow.name
        binding.tvTvshowOverview.text = tvShow.overview
        binding.gradeTvShow.text = tvShow.vote_average
        binding.imgTvshow.loadPoster(tvShow.poster_path)
        binding.imgBackdrop.loadBackdrop(tvShow.backdrop_path)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.appbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
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

    private fun tvShowToFavorite(tvShow: TvShow) {
        favorite = Favorite(
            tvShow.id,
            tvShow.name,
            tvShow.overview,
            tvShow.vote_average,
            tvShow.poster_path,
            tvShow.backdrop_path,
            "tvShow"
        )
    }
}

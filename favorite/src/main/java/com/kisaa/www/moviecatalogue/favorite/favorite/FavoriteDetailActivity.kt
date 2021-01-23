package com.kisaa.www.moviecatalogue.favorite.favorite

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.navArgs
import com.google.android.material.appbar.AppBarLayout
import com.kisaa.www.moviecatalogue.favorite.databinding.ActivityFavoriteDetailBinding
import com.kisaa.www.moviecataloguejetpack.R
import com.kisaa.www.moviecataloguejetpack.core.domain.model.Favorite
import com.kisaa.www.moviecataloguejetpack.core.utils.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.abs

class FavoriteDetailActivity : AppCompatActivity() {

    private val viewModel by viewModel<FavoriteViewModel>()
    private var isFavorite: Boolean = false
    private var menuItem: Menu? = null
    private lateinit var favorite: Favorite
    private lateinit var binding: ActivityFavoriteDetailBinding
    private val args: FavoriteDetailActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        favorite = args.itemFavorite

        binding.detailProgressBar.visible()
        favorite.let {
            viewModel.checkFavoriteById(it.id).observe(this, { data ->
                isFavorite = data != null
            })

            populateView()
            binding.detailProgressBar.invisible()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun populateView() {
        binding.tvFavoriteTitle.text = favorite.title
        binding.tvFavoriteOverview.text = favorite.overview
        binding.grade.text = favorite.voteAverage
        binding.imgFavorite.loadPoster(favorite.posterPath)
        binding.imgBackdrop.loadBackdrop(favorite.backdropPath)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.appbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            title = if (abs(verticalOffset) - appBarLayout.totalScrollRange == 0) {
                favorite.title
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
}
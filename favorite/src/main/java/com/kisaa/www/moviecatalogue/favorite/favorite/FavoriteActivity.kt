package com.kisaa.www.moviecatalogue.favorite.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.kisaa.www.moviecatalogue.favorite.databinding.ActivityFavoriteBinding
import com.kisaa.www.moviecataloguejetpack.core.R
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadKoinModules(favoriteModule)

        supportActionBar?.apply {
            elevation = 0f
            title = "Favorite"
            setDisplayHomeAsUpEnabled(true)
        }

        val pagerAdapter = FavoritePagerAdapter(supportFragmentManager, lifecycle)
        binding.viewPagerFav.adapter = pagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPagerFav) { tab, position ->
            when (position) {
                0 -> tab.text = resources.getString(R.string.movie_title)
                else -> tab.text = resources.getString(R.string.tv_show_title)
            }
        }.attach()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}

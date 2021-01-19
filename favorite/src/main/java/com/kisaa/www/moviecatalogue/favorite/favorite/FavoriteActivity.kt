package com.kisaa.www.moviecatalogue.favorite.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kisaa.www.moviecatalogue.favorite.databinding.ActivityFavoriteBinding
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

        val pagerAdapter = FavoritePagerAdapter(this, supportFragmentManager)
        binding.viewPagerFav.adapter = pagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPagerFav)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}

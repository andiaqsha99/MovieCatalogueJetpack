package com.kisaa.www.moviecatalogue.favorite.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kisaa.www.moviecatalogue.favorite.R
import kotlinx.android.synthetic.main.activity_favorite.*
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        loadKoinModules(favoriteModule)

        supportActionBar?.apply {
            elevation = 0f
            title = "Favorite"
            setDisplayHomeAsUpEnabled(true)
        }

        val pagerAdapter = FavoritePagerAdapter(this, supportFragmentManager)
        view_pager_fav.adapter = pagerAdapter
        tabLayout.setupWithViewPager(view_pager_fav)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
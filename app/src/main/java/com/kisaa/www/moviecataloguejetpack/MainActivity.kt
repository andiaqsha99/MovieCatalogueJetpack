package com.kisaa.www.moviecataloguejetpack

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.kisaa.www.moviecataloguejetpack.favorite.FavoriteActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.elevation = 0f

        val pagerAdapter = ViewPagerAdapter(this, supportFragmentManager)
        view_pager.adapter = pagerAdapter
        tabs.setupWithViewPager(view_pager)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.to_favorite -> {
                startActivity<FavoriteActivity>()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

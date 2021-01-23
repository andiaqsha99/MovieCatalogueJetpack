package com.kisaa.www.moviecatalogue.favorite.favorite

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class FavoritePagerAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                FavoriteListFragment.newInstance("movie")
            }
            1 -> {
                FavoriteListFragment.newInstance("tvshow")
            }
            else -> Fragment()
        }
    }

    override fun getItemCount(): Int = 2
}
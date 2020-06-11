package com.kisaa.www.moviecataloguejetpack.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.kisaa.www.moviecataloguejetpack.R
import com.kisaa.www.moviecataloguejetpack.utils.invisible
import com.kisaa.www.moviecataloguejetpack.utils.visible
import kotlinx.android.synthetic.main.fragment_tv_show_favorite.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class FavoriteTvShowFragment : Fragment() {

    private val viewModel by viewModel<FavoriteViewModel>()
    private lateinit var favAdapter: FavoritePagedAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        favAdapter = FavoritePagedAdapter()

        viewModel.getFavoriteTvShows().observe(viewLifecycleOwner, Observer {
            if (it.isNullOrEmpty()) {
                showEmptyFavorite()
            } else {
                showFavorite()
                favAdapter.submitList(it)
                favAdapter.notifyDataSetChanged()
            }
        })

        with(rv_tvShow_fav) {
            layoutManager = LinearLayoutManager(context)
            adapter = favAdapter
        }
    }

    private fun showFavorite() {
        tv_no_favorite.invisible()
        rv_tvShow_fav.visible()
    }

    private fun showEmptyFavorite() {
        tv_no_favorite.visible()
        rv_tvShow_fav.invisible()
    }
}

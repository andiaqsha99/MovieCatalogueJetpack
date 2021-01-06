package com.kisaa.www.moviecatalogue.favorite.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kisaa.www.moviecatalogue.favorite.R
import com.kisaa.www.moviecataloguejetpack.core.ui.FavoriteAdapter
import com.kisaa.www.moviecataloguejetpack.core.utils.DataMapper
import com.kisaa.www.moviecataloguejetpack.core.utils.invisible
import com.kisaa.www.moviecataloguejetpack.core.utils.visible
import com.kisaa.www.moviecataloguejetpack.tvshow.TvShowDetailActivity
import kotlinx.android.synthetic.main.fragment_tv_show_favorite.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class FavoriteTvShowFragment : Fragment() {

    private val viewModel by viewModel<FavoriteViewModel>()
    private lateinit var favAdapter: FavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        favAdapter = FavoriteAdapter()
        favAdapter.onItemClick = { selectedData ->
            val intent = Intent(activity, TvShowDetailActivity::class.java)
            intent.putExtra(
                TvShowDetailActivity.EXTRA_DATA,
                DataMapper.favoriteToTvShow(selectedData)
            )
            startActivity(intent)
        }

        viewModel.listTvShows.observe(viewLifecycleOwner, {
            if (it.isNullOrEmpty()) {
                showEmptyFavorite()
            } else {
                showFavorite()
                favAdapter.setData(it)
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

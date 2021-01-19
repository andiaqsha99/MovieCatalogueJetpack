package com.kisaa.www.moviecatalogue.favorite.favorite

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kisaa.www.moviecatalogue.favorite.databinding.FragmentMovieFavoriteBinding
import com.kisaa.www.moviecataloguejetpack.core.ui.FavoriteAdapter
import com.kisaa.www.moviecataloguejetpack.core.utils.DataMapper
import com.kisaa.www.moviecataloguejetpack.core.utils.invisible
import com.kisaa.www.moviecataloguejetpack.core.utils.visible
import com.kisaa.www.moviecataloguejetpack.movie.MovieDetailActivity
import com.kisaa.www.moviecataloguejetpack.tvshow.TvShowDetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class FavoriteListFragment : Fragment() {

    companion object {
        const val EXTRA_CATEGORY = "extra_category"
        fun newInstance(category: String): FavoriteListFragment {
            return FavoriteListFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_CATEGORY, category)
                }
            }
        }
    }

    private val viewModel by viewModel<FavoriteViewModel>()
    private lateinit var favAdapter: FavoriteAdapter
    private var _binding: FragmentMovieFavoriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var category: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.getString(EXTRA_CATEGORY)?.let {
            category = it
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favAdapter = FavoriteAdapter()

        if (category == "movie") {
            viewModel.listMovies.observe(viewLifecycleOwner, {
                if (it.isNullOrEmpty()) {
                    showEmptyFavorite()
                } else {
                    showFavorite()
                    favAdapter.setData(it)
                }
            })

            favAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, MovieDetailActivity::class.java)
                intent.putExtra(
                    MovieDetailActivity.EXTRA_DATA,
                    DataMapper.favoriteToMovie(selectedData)
                )
                startActivity(intent)
            }
        } else {
            viewModel.listTvShows.observe(viewLifecycleOwner, {
                if (it.isNullOrEmpty()) {
                    showEmptyFavorite()
                } else {
                    showFavorite()
                    favAdapter.setData(it)
                }
            })

            favAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, TvShowDetailActivity::class.java)
                intent.putExtra(
                    TvShowDetailActivity.EXTRA_DATA,
                    DataMapper.favoriteToTvShow(selectedData)
                )
                startActivity(intent)
            }
        }

        with(binding.rvMovieFav) {
            layoutManager = LinearLayoutManager(context)
            this.adapter = favAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showFavorite() {
        binding.tvNoFavorite.invisible()
        binding.rvMovieFav.visible()
    }

    private fun showEmptyFavorite() {
        binding.tvNoFavorite.visible()
        binding.rvMovieFav.invisible()
    }
}

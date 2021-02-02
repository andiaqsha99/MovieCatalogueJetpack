package com.kisaa.www.moviecatalogue.favorite.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kisaa.www.moviecatalogue.favorite.databinding.FragmentMovieFavoriteBinding
import com.kisaa.www.moviecataloguejetpack.core.ui.FavoriteAdapter
import com.kisaa.www.moviecataloguejetpack.core.utils.invisible
import com.kisaa.www.moviecataloguejetpack.core.utils.visible
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

    private val viewModel: FavoriteViewModel by viewModel()
    private lateinit var favAdapter: FavoriteAdapter
    private var _binding: FragmentMovieFavoriteBinding? = null
    private val binding get() = _binding
    private var category: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieFavoriteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favAdapter = FavoriteAdapter()

        arguments?.takeIf { it.containsKey(EXTRA_CATEGORY) }?.apply {
            category = getString(EXTRA_CATEGORY)
        }

        if (category == "movie") {
            viewModel.listMovies.observe(viewLifecycleOwner, {
                if (it.isNullOrEmpty()) {
                    showEmptyFavorite()
                } else {
                    showFavorite()
                    favAdapter.setData(it)
                }
            })
        } else {
            viewModel.listTvShows.observe(viewLifecycleOwner, {
                if (it.isNullOrEmpty()) {
                    showEmptyFavorite()
                } else {
                    showFavorite()
                    favAdapter.setData(it)
                }
            })
        }

        favAdapter.onItemClick = { selectedData ->
            val toDetailFavorite =
                FavoriteListFragmentDirections.actionFavoriteListFragmentToFavoriteDetailActivity(
                    selectedData
                )
            findNavController().navigate(toDetailFavorite)
        }

        with(binding?.rvMovieFav) {
            this?.layoutManager = LinearLayoutManager(context)
            this?.adapter = favAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.rvMovieFav?.adapter = null
        _binding = null
    }

    private fun showFavorite() {
        binding?.tvNoFavorite?.invisible()
        binding?.rvMovieFav?.visible()
    }

    private fun showEmptyFavorite() {
        binding?.tvNoFavorite?.visible()
        binding?.rvMovieFav?.invisible()
    }
}

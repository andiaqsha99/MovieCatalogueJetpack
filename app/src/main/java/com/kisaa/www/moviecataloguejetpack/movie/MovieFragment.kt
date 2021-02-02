package com.kisaa.www.moviecataloguejetpack.movie


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kisaa.www.moviecataloguejetpack.core.data.Resource
import com.kisaa.www.moviecataloguejetpack.core.ui.MovieAdapter
import com.kisaa.www.moviecataloguejetpack.core.utils.invisible
import com.kisaa.www.moviecataloguejetpack.core.utils.visible
import com.kisaa.www.moviecataloguejetpack.databinding.MovieFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieFragment : Fragment() {

    private lateinit var movieAdapter: MovieAdapter

    private val viewModel by viewModel<MovieViewModel>()
    private var _binding: MovieFragmentBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MovieFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieAdapter = MovieAdapter()
        movieAdapter.onItemClick = { selectedItem ->
            val toDetailMovie =
                MovieFragmentDirections.actionMovieFragmentToMovieDetailActivity(selectedItem)
            findNavController().navigate(toDetailMovie)
        }

        viewModel.listMovies.observe(viewLifecycleOwner, {
            if (it != null) {
                when (it) {
                    is Resource.Loading -> showLoading()
                    is Resource.Success -> {
                        movieAdapter.setData(it.data)
                        hideLoading()
                    }
                    is Resource.Error -> {
                    }
                }
            }
        })

        with(binding?.rvMovie) {
            this?.layoutManager = LinearLayoutManager(context)
            this?.adapter = movieAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.rvMovie?.adapter = null
        _binding = null
    }

    private fun showLoading() {
        binding?.progressBar?.visible()
        binding?.rvMovie?.invisible()
    }

    private fun hideLoading() {
        binding?.progressBar?.invisible()
        binding?.rvMovie?.visible()
    }

}

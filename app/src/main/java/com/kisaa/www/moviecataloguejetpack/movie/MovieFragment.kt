package com.kisaa.www.moviecataloguejetpack.movie


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kisaa.www.moviecataloguejetpack.R
import com.kisaa.www.moviecataloguejetpack.core.data.Resource
import com.kisaa.www.moviecataloguejetpack.core.ui.MovieAdapter
import com.kisaa.www.moviecataloguejetpack.core.utils.invisible
import com.kisaa.www.moviecataloguejetpack.core.utils.visible
import kotlinx.android.synthetic.main.movie_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieFragment : Fragment() {

    private lateinit var movieAdapter: MovieAdapter

    private val viewModel by viewModel<MovieViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        movieAdapter = MovieAdapter()
        movieAdapter.onItemClick = { selectedItem ->
            val intent = Intent(activity, MovieDetailActivity::class.java)
            intent.putExtra(MovieDetailActivity.EXTRA_DATA, selectedItem)
            startActivity(intent)
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

        with(rv_movie) {
            layoutManager = LinearLayoutManager(context)
            adapter = movieAdapter
        }
    }

    private fun showLoading() {
        progress_bar.visible()
        rv_movie.invisible()
    }

    private fun hideLoading() {
        progress_bar.invisible()
        rv_movie.visible()
    }

}

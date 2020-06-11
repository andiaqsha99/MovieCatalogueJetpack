package com.kisaa.www.moviecataloguejetpack.movie


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.kisaa.www.moviecataloguejetpack.R
import com.kisaa.www.moviecataloguejetpack.utils.EspressoIdlingResource
import com.kisaa.www.moviecataloguejetpack.utils.invisible
import com.kisaa.www.moviecataloguejetpack.utils.visible
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

        EspressoIdlingResource.increment()
        showLoading()
        viewModel.getListMovies().observe(viewLifecycleOwner, Observer {
            movieAdapter.setData(it)
            hideLoading()
            EspressoIdlingResource.decrement()
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

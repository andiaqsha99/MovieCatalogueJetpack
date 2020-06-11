package com.kisaa.www.moviecataloguejetpack.tvshow

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
import kotlinx.android.synthetic.main.tv_show_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class TvShowFragment : Fragment() {

    private lateinit var tvShowAdapter: TvShowAdapter

    private val viewModel by viewModel<TvShowViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.tv_show_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tvShowAdapter = TvShowAdapter()

        EspressoIdlingResource.increment()
        showLoading()
        viewModel.getListTvShows().observe(viewLifecycleOwner, Observer {
            tvShowAdapter.setData(it)
            hideLoading()
            EspressoIdlingResource.decrement()
        })

        with(rv_tvShow) {
            layoutManager = LinearLayoutManager(context)
            adapter = tvShowAdapter
        }
    }

    private fun showLoading() {
        progress_bar.visible()
        rv_tvShow.invisible()
    }

    private fun hideLoading() {
        progress_bar.invisible()
        rv_tvShow.visible()
    }
}

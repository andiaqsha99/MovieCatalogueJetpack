package com.kisaa.www.moviecataloguejetpack.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kisaa.www.moviecataloguejetpack.R
import com.kisaa.www.moviecataloguejetpack.core.data.Resource
import com.kisaa.www.moviecataloguejetpack.core.ui.TvShowAdapter
import com.kisaa.www.moviecataloguejetpack.core.utils.invisible
import com.kisaa.www.moviecataloguejetpack.core.utils.visible
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
        tvShowAdapter.onItemClick = { selectedData ->
            val intent = Intent(activity, TvShowDetailActivity::class.java)
            intent.putExtra(TvShowDetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        viewModel.listTvShow.observe(viewLifecycleOwner, {
            if (it != null) {
                when (it) {
                    is Resource.Loading -> showLoading()
                    is Resource.Success -> {
                        tvShowAdapter.setData(it.data)
                        hideLoading()
                    }
                    is Resource.Error -> {
                    }
                }
            }


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

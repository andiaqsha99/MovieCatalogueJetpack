package com.kisaa.www.moviecataloguejetpack.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kisaa.www.moviecataloguejetpack.core.data.Resource
import com.kisaa.www.moviecataloguejetpack.core.ui.TvShowAdapter
import com.kisaa.www.moviecataloguejetpack.core.utils.invisible
import com.kisaa.www.moviecataloguejetpack.core.utils.visible
import com.kisaa.www.moviecataloguejetpack.databinding.TvShowFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class TvShowFragment : Fragment() {

    private lateinit var tvShowAdapter: TvShowAdapter

    private val viewModel by viewModel<TvShowViewModel>()
    private var _binding: TvShowFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TvShowFragmentBinding.inflate(inflater, container, false)
        return binding.root
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

        with(binding.rvTvShow) {
            layoutManager = LinearLayoutManager(context)
            adapter = tvShowAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showLoading() {
        binding.progressBar.visible()
        binding.rvTvShow.invisible()
    }

    private fun hideLoading() {
        binding.progressBar.invisible()
        binding.rvTvShow.visible()
    }
}

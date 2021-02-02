package com.kisaa.www.moviecataloguejetpack.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
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
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TvShowFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvShowAdapter = TvShowAdapter()
        tvShowAdapter.onItemClick = { selectedData ->
            val toDetailTvShow =
                TvShowFragmentDirections.actionTvShowFragmentToTvShowDetailActivity(selectedData)
            findNavController().navigate(toDetailTvShow)
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

        with(binding?.rvTvShow) {
            this?.layoutManager = LinearLayoutManager(context)
            this?.adapter = tvShowAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.rvTvShow?.adapter = null
        _binding = null
    }

    private fun showLoading() {
        binding?.progressBar?.visible()
        binding?.rvTvShow?.invisible()
    }

    private fun hideLoading() {
        binding?.progressBar?.invisible()
        binding?.rvTvShow?.visible()
    }
}

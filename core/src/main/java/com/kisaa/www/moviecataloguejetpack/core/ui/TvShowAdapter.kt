package com.kisaa.www.moviecataloguejetpack.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kisaa.www.moviecataloguejetpack.core.R
import com.kisaa.www.moviecataloguejetpack.core.databinding.ItemListBinding
import com.kisaa.www.moviecataloguejetpack.core.domain.model.TvShow
import com.kisaa.www.moviecataloguejetpack.core.utils.loadPoster

class TvShowAdapter :
    RecyclerView.Adapter<TvShowAdapter.TvViewHolder>() {

    private var tvShows = ArrayList<TvShow>()
    var onItemClick: ((TvShow) -> Unit)? = null

    fun setData(data: List<TvShow>?) {
        if (data == null) return
        tvShows.clear()
        tvShows.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        val mView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return TvViewHolder(mView)
    }

    override fun getItemCount() = tvShows.size

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        holder.bindItem(tvShows[position])
    }

    inner class TvViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemListBinding.bind(itemView)
        fun bindItem(tvShow: TvShow) {
            with(binding) {
                txtTitle.text = tvShow.name
                txtDescription.text = tvShow.overview
                txtGrade.text = tvShow.vote_average
                imgPhoto.loadPoster(tvShow.poster_path)
                root.setOnClickListener {
                    onItemClick?.invoke(tvShows[adapterPosition])
                }
            }
        }
    }
}
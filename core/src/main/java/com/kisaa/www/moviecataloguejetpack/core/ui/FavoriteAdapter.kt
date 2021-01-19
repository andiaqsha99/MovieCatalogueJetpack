package com.kisaa.www.moviecataloguejetpack.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kisaa.www.moviecataloguejetpack.core.R
import com.kisaa.www.moviecataloguejetpack.core.databinding.ItemListBinding
import com.kisaa.www.moviecataloguejetpack.core.domain.model.Favorite
import com.kisaa.www.moviecataloguejetpack.core.utils.loadPoster

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private val favorites = ArrayList<Favorite>()
    var onItemClick: ((Favorite) -> Unit)? = null

    fun setData(data: List<Favorite>?) {
        if (data == null) return
        favorites.clear()
        favorites.addAll(data)
        notifyDataSetChanged()
    }

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListBinding.bind(itemView)
        fun bind(favorite: Favorite) {
            with(binding) {
                txtTitle.text = favorite.title
                txtDescription.text = favorite.overview
                txtGrade.text = favorite.voteAverage
                imgPhoto.loadPoster(favorite.posterPath)
                root.setOnClickListener {
                    onItemClick?.invoke(favorites[adapterPosition])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(favorites[position])
    }

    override fun getItemCount(): Int = favorites.size
}
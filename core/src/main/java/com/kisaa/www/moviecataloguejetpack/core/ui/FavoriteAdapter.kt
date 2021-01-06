package com.kisaa.www.moviecataloguejetpack.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kisaa.www.moviecataloguejetpack.core.R
import com.kisaa.www.moviecataloguejetpack.core.domain.model.Favorite
import com.kisaa.www.moviecataloguejetpack.core.utils.loadPoster
import kotlinx.android.synthetic.main.item_list.view.*

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
        fun bind(favorite: Favorite) {
            with(itemView) {
                txt_title.text = favorite.title
                txt_description.text = favorite.overview
                txt_grade.text = favorite.voteAverage
                img_photo.loadPoster(favorite.posterPath)
                setOnClickListener {
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
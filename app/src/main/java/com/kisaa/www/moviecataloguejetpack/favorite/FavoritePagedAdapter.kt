package com.kisaa.www.moviecataloguejetpack.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kisaa.www.moviecataloguejetpack.R
import com.kisaa.www.moviecataloguejetpack.data.source.local.FavoriteEntity
import com.kisaa.www.moviecataloguejetpack.movie.MovieDetailActivity
import com.kisaa.www.moviecataloguejetpack.tvshow.TvShowDetailActivity
import com.kisaa.www.moviecataloguejetpack.utils.loadPoster
import kotlinx.android.synthetic.main.item_list.view.*
import org.jetbrains.anko.startActivity

class FavoritePagedAdapter internal constructor() :
    PagedListAdapter<FavoriteEntity, FavoritePagedAdapter.FavoriteViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FavoriteEntity>() {
            override fun areItemsTheSame(
                oldItem: FavoriteEntity,
                newItem: FavoriteEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: FavoriteEntity,
                newItem: FavoriteEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(favorite: FavoriteEntity) {
            with(itemView) {
                txt_title.text = favorite.title
                txt_description.text = favorite.overview
                txt_grade.text = favorite.voteAverage
                img_photo.loadPoster(favorite.posterPath)
                setOnClickListener {
                    when (favorite.category) {
                        "movie" -> itemView.context.startActivity<MovieDetailActivity>(
                            MovieDetailActivity.EXTRA_ID to favorite.id
                        )
                        "tvShow" -> itemView.context.startActivity<TvShowDetailActivity>(
                            TvShowDetailActivity.EXTRA_ID to favorite.id
                        )
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val favorite = getItem(position)
        if (favorite != null) {
            holder.bind(favorite)
        }
    }
}
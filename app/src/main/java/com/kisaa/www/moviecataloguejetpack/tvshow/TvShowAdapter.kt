package com.kisaa.www.moviecataloguejetpack.tvshow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kisaa.www.moviecataloguejetpack.R
import com.kisaa.www.moviecataloguejetpack.data.source.remote.TvShowEntity
import com.kisaa.www.moviecataloguejetpack.utils.loadPoster
import kotlinx.android.synthetic.main.item_list.view.*
import org.jetbrains.anko.startActivity

class TvShowAdapter :
    RecyclerView.Adapter<TvShowAdapter.TvViewHolder>() {

    private var tvShows = ArrayList<TvShowEntity>()

    fun setData(data: List<TvShowEntity>) {
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
        fun bindItem(tvShow: TvShowEntity) {
            with(itemView) {
                txt_title.text = tvShow.name
                txt_description.text = tvShow.overview
                txt_grade.text = tvShow.vote_average
                img_photo.loadPoster(tvShow.poster_path)
                setOnClickListener {
                    itemView.context.startActivity<TvShowDetailActivity>(
                        TvShowDetailActivity.EXTRA_ID to tvShow.id
                    )
                }
            }
        }
    }
}
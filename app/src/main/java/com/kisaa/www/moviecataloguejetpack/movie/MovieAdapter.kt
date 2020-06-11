package com.kisaa.www.moviecataloguejetpack.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kisaa.www.moviecataloguejetpack.R
import com.kisaa.www.moviecataloguejetpack.data.source.remote.MovieEntity
import com.kisaa.www.moviecataloguejetpack.utils.loadPoster
import kotlinx.android.synthetic.main.item_list.view.*
import org.jetbrains.anko.startActivity

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val movies = ArrayList<MovieEntity>()

    fun setData(data: List<MovieEntity>) {
        movies.clear()
        movies.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_list,
            parent, false
        )
        return MovieViewHolder(mView)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindItem(movies[position])
    }

    inner class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(movie: MovieEntity) {
            with(itemView) {
                txt_title.text = movie.title
                txt_description.text = movie.overview
                txt_grade.text = movie.vote_average
                img_photo.loadPoster(movie.poster_path)
                setOnClickListener {
                    itemView.context.startActivity<MovieDetailActivity>(
                        MovieDetailActivity.EXTRA_ID to movie.id
                    )
                }
            }
        }
    }
}
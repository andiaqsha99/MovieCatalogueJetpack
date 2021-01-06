package com.kisaa.www.moviecataloguejetpack.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kisaa.www.moviecataloguejetpack.core.R
import com.kisaa.www.moviecataloguejetpack.core.domain.model.Movie
import com.kisaa.www.moviecataloguejetpack.core.utils.loadPoster
import kotlinx.android.synthetic.main.item_list.view.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val movies = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setData(data: List<Movie>?) {
        if (data == null) return
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
        fun bindItem(movie: Movie) {
            with(itemView) {
                txt_title.text = movie.title
                txt_description.text = movie.overview
                txt_grade.text = movie.vote_average
                img_photo.loadPoster(movie.poster_path)
                setOnClickListener {
                    onItemClick?.invoke(movies[adapterPosition])
                }
            }
        }
    }
}
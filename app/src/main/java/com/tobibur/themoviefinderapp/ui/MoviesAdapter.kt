package com.tobibur.themoviefinderapp.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tobibur.themoviefinderapp.R
import com.tobibur.themoviefinderapp.data.api.ApiConstants
import com.tobibur.themoviefinderapp.data.model.Movie
import com.tobibur.themoviefinderapp.utils.inflate
import com.tobibur.themoviefinderapp.utils.load
import kotlinx.android.synthetic.main.movie_recycler_item.view.*

class MoviesAdapter(private val movieList: MutableList<Movie>)
    : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>(){

    inner class MoviesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    fun addMovies(list: List<Movie>){
        movieList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(parent.inflate(R.layout.movie_recycler_item))
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = movieList[position]
        holder.itemView.apply {
            cv_movie_title.text = movie.title
            cv_movie_release_date.text = movie.releaseDate
            val moviePosterURL = ApiConstants.POSTER_BASE_URL + movie.posterPath
            cv_iv_movie_poster.load(moviePosterURL, context)
        }
    }
}
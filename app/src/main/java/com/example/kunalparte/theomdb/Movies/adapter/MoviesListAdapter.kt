package com.example.kunalparte.theomdb.Movies.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kunalparte.sundaymobtask.utils.GlideLoader
import com.example.kunalparte.theomdb.Movies.Utils.interfaces.OnRecyclerViewItemClicked
import com.example.kunalparte.theomdb.Movies.model.Movie
import com.example.kunalparte.theomdb.Movies.model.MoviesList
import com.example.kunalparte.theomdb.R
import kotlinx.android.synthetic.main.movies_list_item_layout.view.*

class MoviesListAdapter constructor(context: Context,recyclerViewItemClicked: OnRecyclerViewItemClicked) : RecyclerView.Adapter<MoviesListAdapter.VHolder>() {

    var context = context
    var moviesList: ArrayList<Movie> = ArrayList()
    var onRecyclerViewItemClicked = recyclerViewItemClicked

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): VHolder {
        return VHolder(LayoutInflater.from(parent.context).inflate(R.layout.movies_list_item_layout,parent,false))
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        GlideLoader.url(context).load(moviesList.get(position).Poster).into(holder.itemView.movie_image_view)
        holder.itemView.movie_name_tv.setText(moviesList.get(position).Title)
        holder.itemView.movie_year_tv.setText(moviesList.get(position).Year)

        holder.itemView.setOnClickListener(View.OnClickListener {
            onRecyclerViewItemClicked.onItemClickListener(position)
        })
    }


    class VHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}
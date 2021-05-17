package com.saveo.assignment.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.saveo.assignment.R
import com.saveo.assignment.model.response.Genre

class MovieInfoAdapter : RecyclerView.Adapter<MovieInfoAdapter.MyViewHolder>() {

    lateinit var movieInfo: ArrayList<Genre>

    fun setData(list: ArrayList<Genre>) {
        movieInfo = list
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieInfoAdapter.MyViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.genres_layout, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieInfoAdapter.MyViewHolder, position: Int) {
        val result = movieInfo[position]
        result.let {
            holder.genres.text = result.name
        }
    }

    override fun getItemCount() = movieInfo.size

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val genres: TextView = itemView.findViewById(R.id.genresName)
    }
}
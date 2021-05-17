package com.saveo.assignment.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.saveo.assignment.Constants.IMAGE_BUCKET_URL
import com.saveo.assignment.MovieItemListener
import com.saveo.assignment.R
import com.saveo.assignment.model.response.Results

class MovieListAdapter( private val clickListener: MovieItemListener): RecyclerView.Adapter<MovieListAdapter.MyViewHolder>() {

    lateinit var movieInfoList: ArrayList<Results>
    lateinit var mContext: Context

    fun setData(list: ArrayList<Results>, mContext: Context) {
        movieInfoList = list
        this.mContext = mContext
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListAdapter.MyViewHolder {

        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_name_layout, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieListAdapter.MyViewHolder, position: Int) {
        val result = movieInfoList[position]
        result.let{
            Glide.with(mContext)
                .load(IMAGE_BUCKET_URL + result.poster_path)
                .placeholder(R.drawable.film)
                .into(holder.moviePoster)

            holder.itemView.setOnClickListener{
                val id = movieInfoList[position]
                clickListener.onItemListener(id)
            }
        }
    }
    override fun getItemCount() = movieInfoList.size

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var moviePoster : ImageView =  itemView.findViewById(R.id.poster)
    }
}
package com.saveo.assignment.views.activities

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.saveo.assignment.Constants.API_KEY
import com.saveo.assignment.Constants.IMAGE_BUCKET_URL
import com.saveo.assignment.R
import com.saveo.assignment.databinding.ActivityMovieDetailsBinding
import com.saveo.assignment.model.response.Genre
import com.saveo.assignment.viewmodel.MoviesViewModel
import com.saveo.assignment.views.adapters.MovieInfoAdapter

class MovieDetailsActivity : BaseActivity<ActivityMovieDetailsBinding>() {

    private val movieViewModel: MoviesViewModel by viewModels()
    private var movieId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpContentView(R.layout.activity_movie_details)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        setToolbarTitle("Movie Details")
        setupObservers()
        movieId = intent.getIntExtra("movieId", 0)
        movieDetails(movieId)

    }


    private fun movieDetails(id: Int) {
        movieViewModel.getMovieInfo(id, API_KEY)
    }

    override fun setupViewModel() {
        mainViewDatBinding.setVariable(BR.viewModel, movieViewModel)
        mainViewDatBinding.lifecycleOwner = this

    }

    private fun setupObservers() {
        movieViewModel.handleMoviesInfo.observe(this, { response ->
            if (response != null) {
                mainViewDatBinding.movieName.text = response.original_title
                mainViewDatBinding.durationDate.text =
                    response.runtime.toString() + " mins | " + response.release_date

                mainViewDatBinding.ratingTv.text =
                    "Rating: " + ((response.vote_average) / 2).toString()

                mainViewDatBinding.reviewsTv.text = "Reviews: " + response.vote_count.toString()

                mainViewDatBinding.overViewTV.text = response.overview
                Glide.with(this)
                    .load(IMAGE_BUCKET_URL + response.poster_path)
                    .placeholder(R.drawable.film)
                    .into(mainViewDatBinding.poster)


                if (response.genres != null) {
                    mainViewDatBinding.genresType.visibility = View.VISIBLE
                    val adapter = MovieInfoAdapter()
                    adapter.setData(response.genres as ArrayList<Genre>)
                    mainViewDatBinding.genresType.layoutManager = GridLayoutManager(this, 2)
                    mainViewDatBinding.genresType.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
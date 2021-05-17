package com.saveo.assignment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.GridLayoutManager
import com.saveo.assignment.Constants.API_KEY
import com.saveo.assignment.databinding.ActivityHomeBinding
import com.saveo.assignment.model.response.Results
import com.saveo.assignment.viewmodel.MoviesViewModel
import com.saveo.assignment.views.activities.BaseActivity
import com.saveo.assignment.views.activities.MovieDetailsActivity
import com.saveo.assignment.views.adapters.MovieListAdapter

class HomeActivity : BaseActivity<ActivityHomeBinding>(), MovieItemListener {

    private val moviesViewModel: MoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolbarTitle("Movies")
        setUpContentView(R.layout.activity_home)
        getMovieList()
        setupObservers()

    }

    private fun getMovieList() {
        moviesViewModel.getMoviesList(API_KEY)
    }

    private fun setupObservers() {
        moviesViewModel.handleMoviesList.observe(this, {
            if (!it.isNullOrEmpty()) {
                mainViewDatBinding.movieListRV.visibility = View.VISIBLE
                val adapter = MovieListAdapter(this)
                adapter.setData(it as ArrayList<Results>, this)
                mainViewDatBinding.movieListRV.layoutManager = GridLayoutManager(this, 3)
                mainViewDatBinding.movieListRV.adapter = adapter
                adapter.notifyDataSetChanged()
            }
        })

        moviesViewModel.handleFailure.observe(this, {
            if (it) {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun setupViewModel() {
        mainViewDatBinding.setVariable(BR.viewModel, moviesViewModel)
        mainViewDatBinding.lifecycleOwner = this
    }

    override fun onItemListener(result: Results) {

        Log.d(TAG, "itemClicked" + result)
        val intent = Intent(this, MovieDetailsActivity::class.java)
        intent.putExtra("movieId", result.id)
        startActivity(intent)
    }

    companion object {
        private val TAG = MovieDetailsActivity::class.java.simpleName
    }

}

interface MovieItemListener {
    fun onItemListener(result: Results)
}
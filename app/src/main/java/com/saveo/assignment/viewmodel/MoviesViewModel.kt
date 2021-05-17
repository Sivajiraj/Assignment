package com.saveo.assignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.saveo.assignment.model.response.MovieInfoResponse
import com.saveo.assignment.model.response.MoviesListResponse
import com.saveo.assignment.model.response.Results
import com.saveo.assignment.network.ApiRetrofitClient
import com.saveo.assignment.network.IApiResponse
import com.saveo.assignment.repository.MovieListRepository

class MoviesViewModel : ViewModel(){

    private val repository = MovieListRepository(ApiRetrofitClient.baseApiInterface)

    private val _handleMoviesList = MutableLiveData<List<Results>>()
    val handleMoviesList: LiveData<List<Results>> = _handleMoviesList

    private val _handleMoviesInfo = MutableLiveData<MovieInfoResponse>()
    val handleMoviesInfo: LiveData<MovieInfoResponse> = _handleMoviesInfo


    private val _handleFailure = MutableLiveData<Boolean>()
    val handleFailure: LiveData<Boolean> = _handleFailure

    fun setHandleSuccess(value: List<Results>) {
        _handleMoviesList.value = value
    }
    fun setHandleInfoSuccess(value:MovieInfoResponse ) {
        _handleMoviesInfo.value = value
    }
    fun setHandleFailure(value: Boolean) {
        _handleFailure.value = value
    }

    fun getMoviesList(key : String) {
        repository.getMovieList(key, object : IApiResponse {
            override fun onSuccess(response: Any) {
                if (response is MoviesListResponse) {
                    setHandleSuccess(response.results)
                } else {
                    setHandleFailure(true)
                }
            }

            override fun onFailure() {
                setHandleFailure(true)
            }

        })
    }

    fun getMovieInfo(id : Int,key: String){
        repository.getMovieInfo(id,key, object : IApiResponse {
            override fun onSuccess(response: Any) {
                if (response is MovieInfoResponse) {
                    setHandleInfoSuccess(response)
                } else {
                    setHandleFailure(true)
                }
            }

            override fun onFailure() {
                setHandleFailure(true)
            }

        })
    }
}
package com.saveo.assignment.repository

import com.saveo.assignment.model.response.MovieInfoResponse
import com.saveo.assignment.model.response.MoviesListResponse
import com.saveo.assignment.network.BaseApiInterface
import com.saveo.assignment.network.IApiResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieListRepository(val baseApiInterface: BaseApiInterface) : IMovieListRepository {

    override fun getMovieList(key: String, apiResponse: IApiResponse) {

        CoroutineScope(Dispatchers.IO).launch {
            val call = baseApiInterface.getMovieList(key)
            call.enqueue(object : Callback<MoviesListResponse> {
                override fun onResponse(
                    call: Call<MoviesListResponse>,
                    response: Response<MoviesListResponse>
                ) {
                    if (response.body() != null) {
                        apiResponse.onSuccess(response.body() as MoviesListResponse)
                    } else {
                        apiResponse.onFailure()
                    }
                }

                override fun onFailure(call: Call<MoviesListResponse>, t: Throwable) {
                    apiResponse.onFailure()
                }
            })
        }
    }

    override fun getMovieInfo(id: Int, key: String, apiResponse: IApiResponse) {

        CoroutineScope(Dispatchers.IO).launch {
            val call = baseApiInterface.getMovieInfo(id, key)
            call.enqueue(object : Callback<MovieInfoResponse> {
                override fun onResponse(
                    call: Call<MovieInfoResponse>,
                    response: Response<MovieInfoResponse>
                ) {
                    if (response.body() != null) {
                        apiResponse.onSuccess(response.body() as MovieInfoResponse)
                    } else {
                        apiResponse.onFailure()
                    }
                }

                override fun onFailure(call: Call<MovieInfoResponse>, t: Throwable) {
                    apiResponse.onFailure()
                }
            })
        }

    }
}
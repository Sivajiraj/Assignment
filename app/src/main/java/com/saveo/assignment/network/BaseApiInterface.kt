package com.saveo.assignment.network

import com.saveo.assignment.model.response.MovieInfoResponse
import com.saveo.assignment.model.response.MoviesListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BaseApiInterface {

    @GET("now_playing?")
    fun getMovieList(@Query("api_key") key: String): Call<MoviesListResponse>
    @GET("{id}?")
    fun getMovieInfo(@Path("id") id : Int, @Query("api_key") key: String ): Call<MovieInfoResponse>
}
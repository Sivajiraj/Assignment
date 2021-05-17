package com.saveo.assignment.repository

import com.saveo.assignment.network.IApiResponse

interface IMovieListRepository {
    fun getMovieList(key: String,apiResponse: IApiResponse)

    fun getMovieInfo(id: Int,key: String,apiResponse: IApiResponse)
}
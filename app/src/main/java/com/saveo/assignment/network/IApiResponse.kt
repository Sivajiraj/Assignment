package com.saveo.assignment.network

interface IApiResponse {
    fun onSuccess(response: Any)
    fun onFailure()
}
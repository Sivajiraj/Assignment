package com.saveo.assignment.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.saveo.assignment.Constants.API_BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

object ApiRetrofitClient {

    //Create retrofit instance
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
    }

    //okhttp client instance
    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient().newBuilder()
                .addInterceptor(interceptor)
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build()
    }
    private val interceptor: HttpLoggingInterceptor by lazy {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    private val gson: Gson by lazy {
        GsonBuilder().setLenient().create()
    }

    val baseApiInterface: BaseApiInterface by lazy {
        retrofit.create(BaseApiInterface::class.java)
    }
}
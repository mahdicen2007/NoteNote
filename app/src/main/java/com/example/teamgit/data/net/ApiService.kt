package com.example.teamgit.data.net

import com.example.teamgit.data.model.BlogResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {

}

fun createApiService() :ApiService {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(ApiService::class.java)

}
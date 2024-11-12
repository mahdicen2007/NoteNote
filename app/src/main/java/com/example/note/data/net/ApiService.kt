package com.example.note.data.net

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface ApiService {

}

fun createApiService() :ApiService {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(ApiService::class.java)

}
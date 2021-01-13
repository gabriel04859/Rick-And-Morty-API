package com.gabriel.rickandmortyapi.domain

import com.gabriel.rickandmortyapi.utils.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofitInstance by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    val apiService  : ApiService by lazy {
        retrofitInstance.create(ApiService::class.java)
    }

}
package com.example.firstapp.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UserApiInstance {
    val api:UserApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserApi::class.java)
    }
}
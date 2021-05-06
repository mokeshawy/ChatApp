package com.example.chatapp.retrofitbuilder

import com.example.chatapp.`interface`.ConnectionEndPoint
import com.example.chatapp.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {

    fun makeRetrofit() : ConnectionEndPoint{

        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ConnectionEndPoint::class.java)
    }
}
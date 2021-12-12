package com.example.exampletestingproyect.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIClient {

    companion object{
        private const val API_BASE_URL = "https://dog.ceo/api/"

        fun getClient(): Retrofit? {
            val client: OkHttpClient = OkHttpClient.Builder().build()
            return Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }
    }

}
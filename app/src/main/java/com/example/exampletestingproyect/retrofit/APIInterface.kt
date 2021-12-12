package com.example.exampletestingproyect.retrofit

import com.example.exampletestingproyect.models.GeneralGetHttpModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface APIInterface {
    @GET("breeds/list")
    fun getAllListDogs(): Call<GeneralGetHttpModel>

    @GET
    fun getSubBreeds(@Url url: String): Call<GeneralGetHttpModel>

    @GET
    fun getImageFromDogs(@Url url: String): Call<GeneralGetHttpModel>
}
package com.example.exampletestingproyect.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GeneralGetHttpModel {
    @SerializedName("message")
    @Expose
    var message: List<String>? = null

    @SerializedName("status")
    @Expose
    var status: String? = null
}
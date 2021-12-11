package com.example.exampletestingproyect.models

import java.util.Date

data class DataModelClass(
    val id: String,
    val phone: Int,
    val name: String,
    val surName: String,
    val balance: Double,
    val date: Date?
)
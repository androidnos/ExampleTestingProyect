package com.example.exampletestingproyect.models

data class BreedModel(
    val name: String,
    val listTypeBreed: ArrayList<String> = arrayListOf()
)
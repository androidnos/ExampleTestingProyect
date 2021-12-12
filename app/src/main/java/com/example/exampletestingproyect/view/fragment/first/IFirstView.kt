package com.example.exampletestingproyect.view.fragment.first

import com.example.exampletestingproyect.models.BreedModel

interface IFirstView {

    fun addListAdapter(list: List<BreedModel>)
    fun updateSublist(name: String, list: List<String>?)
    fun showLoading()
    fun hidenLoading()
    fun showError()
}
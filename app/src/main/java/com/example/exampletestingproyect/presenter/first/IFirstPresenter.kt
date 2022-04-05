package com.example.exampletestingproyect.presenter.first

import com.example.exampletestingproyect.view.fragment.first.IFirstView

interface IFirstPresenter {

    fun attactView(view: IFirstView)
    fun callAllList()
    fun callSubBreed(breed: String)
}
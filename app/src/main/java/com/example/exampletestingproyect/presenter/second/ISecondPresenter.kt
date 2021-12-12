package com.example.exampletestingproyect.presenter.second

import com.example.exampletestingproyect.view.fragment.secund.ISecondView

interface ISecondPresenter {

    fun attactView(view: ISecondView)
    fun callBreedImage(name: String)
}
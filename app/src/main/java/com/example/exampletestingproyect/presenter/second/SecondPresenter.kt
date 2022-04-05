package com.example.exampletestingproyect.presenter.second

import com.example.exampletestingproyect.models.GeneralGetHttpModel
import com.example.exampletestingproyect.retrofit.APIClient
import com.example.exampletestingproyect.retrofit.APIInterface
import com.example.exampletestingproyect.view.fragment.secund.ISecondView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SecondPresenter : ISecondPresenter {

    private lateinit var view: ISecondView
    private lateinit var apiInterface: APIInterface

    override fun attactView(view: ISecondView) {
        this.view = view
        apiInterface = APIClient.getClient().create(APIInterface::class.java)
    }

    override fun callBreedImage(name: String) {
        val item = name.split("-")
        val call = apiInterface.getImageFromDogs("breed/${item[0]}/images")
        call.enqueue(object : Callback<GeneralGetHttpModel> {
            override fun onResponse(
                call: Call<GeneralGetHttpModel>,
                response: Response<GeneralGetHttpModel>
            ) {
                response.body()?.message?.let {
                    val arrayList = arrayListOf<String>()
                    it.forEach { item ->
                        if (item.contains(name)) {
                            arrayList.add(item)
                        }
                    }
                    if (arrayList.isNotEmpty()) {
                        view.initImageView((arrayList as List<String>).random())
                        view.hidenLoading()
                    } else {
                        view.showError()
                    }
                } ?: view.showError()
            }

            override fun onFailure(call: Call<GeneralGetHttpModel>, t: Throwable) {
                view.showError()
            }
        })
    }
}
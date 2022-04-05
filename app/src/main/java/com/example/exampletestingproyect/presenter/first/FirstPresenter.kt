package com.example.exampletestingproyect.presenter.first

import com.example.exampletestingproyect.models.BreedModel
import com.example.exampletestingproyect.models.GeneralGetHttpModel
import com.example.exampletestingproyect.retrofit.APIInterface
import com.example.exampletestingproyect.view.fragment.first.IFirstView

import com.example.exampletestingproyect.retrofit.APIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * En estos test se invoca a varios servicios, para poder testear todas las partes de cada llamada necesitaremos hacer varios test
 * por cada llamada a servicio tenemos un onResponse y un onFailure estos habrá que testearlos
 * por cada if, nulable, etc... que fraccione la posible salida del recorrido habrá que hacer un test para poder tener la mayor cobertura de testeo
 * por cada test que hagamos es obligatorio realizar una verificación de donde termina el test
 */
class FirstPresenter : IFirstPresenter {

    private lateinit var view: IFirstView
    private lateinit var apiInterface: APIInterface

    override fun attactView(view: IFirstView) {
        this.view = view
        apiInterface = APIClient.getClient().create(APIInterface::class.java)
        callAllList()
    }

    override fun callAllList() {
        view.showLoading()
        val call = apiInterface.getAllListDogs()
        call.enqueue(object : Callback<GeneralGetHttpModel> {
            override fun onResponse(
                call: Call<GeneralGetHttpModel>,
                response: Response<GeneralGetHttpModel>
            ) {
                response.body()?.message?.let {
                    val listBreedModel = arrayListOf<BreedModel>()
                    it.forEach { name ->
                        listBreedModel.add(BreedModel(name))
                    }
                    view.addListAdapter(listBreedModel)
                    view.hidenLoading()
                } ?: view.showError()
            }

            override fun onFailure(call: Call<GeneralGetHttpModel>, t: Throwable) {
                view.showError()
            }
        })
    }

    override fun callSubBreed(breed: String) {
        view.showLoading()
        val call = apiInterface.getSubBreeds("breed/$breed/list")
        call.enqueue(object : Callback<GeneralGetHttpModel> {
            override fun onResponse(
                call: Call<GeneralGetHttpModel>,
                response: Response<GeneralGetHttpModel>
            ) {
                response.body()?.let {
                    view.updateSublist(breed, it.message)
                    view.hidenLoading()
                } ?: view.showError()
            }

            override fun onFailure(call: Call<GeneralGetHttpModel>, t: Throwable) {
                view.showError()
            }
        })
    }
}
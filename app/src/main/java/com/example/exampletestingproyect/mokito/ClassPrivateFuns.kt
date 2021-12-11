package com.example.exampletestingproyect.mokito

import com.example.exampletestingproyect.models.DataModelClass
import java.util.Calendar

class ClassPrivateFuns() {

    private lateinit var listDataModelClass: ArrayList<DataModelClass>

    private fun getNewDataModel(): DataModelClass{
        val date = Calendar.getInstance().time
        return DataModelClass(
            "1",
            678678563,
            "Nombre2",
            "Apellido1 Apellido2",
            0.0,
            date
        )
    }

    private fun addNewDataModel(dataModelClass: DataModelClass){
        listDataModelClass.add(dataModelClass)
    }

    private fun initList(){
        listDataModelClass = arrayListOf()
    }

    fun callAddNewDataModel(){
        addNewDataModel(getNewDataModel())
    }

}
package com.example.exampletestingproyect.mokito

import com.example.exampletestingproyect.models.DataModelClass
import com.nhaarman.mockitokotlin2.mock
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ClassPrivateFunsTest {

    private lateinit var classPrivateFunsTest: ClassPrivateFuns

    @Before
    fun setUp() {

        classPrivateFunsTest = ClassPrivateFuns()
    }

    private fun setPrivateField(name: String, item: Any) {
        val field = classPrivateFunsTest.javaClass.getDeclaredField(name)
        field.isAccessible = true
        field.set(classPrivateFunsTest, item)
    }

    private fun getPrivateField(name: String): Any? {
        val field = classPrivateFunsTest.javaClass.getDeclaredField(name)
        field.isAccessible = true
        return field.get(classPrivateFunsTest)
    }

    private fun invokePrivateFun(
        name: String,
        parameters: Array<Any?> = arrayOf(),
        vararg parameterTypes: Class<*>?
    ): Any? {
        val method = classPrivateFunsTest.javaClass.getDeclaredMethod(name, *parameterTypes)
        method.isAccessible = true
        return method.invoke(classPrivateFunsTest, *parameters)
    }

    @Test
    fun addNewDataModel() {
        /* Given */
        val mockDataModelClass: DataModelClass = mock()
        val parameters = arrayOfNulls<Any>(1)
        parameters[0] = mockDataModelClass

        /* When */
        setPrivateField("listDataModelClass", arrayListOf<DataModelClass>())
        invokePrivateFun("addNewDataModel", parameters, DataModelClass::class.java)
        val itemUptdate = getPrivateField("listDataModelClass") as List<*>

        /* Then */
        assertTrue(itemUptdate.isNotEmpty())
    }

    @Test
    fun initList() {
        /* When */
        invokePrivateFun("initList")
        val itemUptdate = getPrivateField("listDataModelClass") as List<*>

        /* Then */
        assertNotNull(itemUptdate)
        assertTrue(itemUptdate.isEmpty())
    }

}
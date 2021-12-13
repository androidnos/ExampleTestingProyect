package com.example.exampletestingproyect.presenter.first

import com.example.exampletestingproyect.BaseTestPresenterTest
import com.example.exampletestingproyect.models.GeneralGetHttpModel
import com.example.exampletestingproyect.retrofit.APIInterface
import com.example.exampletestingproyect.view.fragment.first.IFirstView
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * En estos test se invoca a varios servicios, para poder testear todas las partes de cada llamada necesitaremos hacer varios test
 * por cada llamada a servicio tenemos un onResponse y un onFailure estos habrá que testearlos
 * por cada if, nulable, etc... que fraccione la posible salida del recorrido habrá que hacer un test para poder tener la mayor cobertura de testeo
 * por cada test que hagamos es obligatorio realizar una verificación de donde termina el test
 */
class FirstPresenterTest : BaseTestPresenterTest() {

    private lateinit var presenterTest: FirstPresenter
    private val view: IFirstView = mock()
    private val apiInterface: APIInterface = mock()

    @Before
    override fun setUp() {
        super.setUp()

        presenterTest = FirstPresenter()
        presenterTest.attactView(view)
        presenterTest.setPropertyValue("apiInterface", apiInterface)
    }

    @Test
    fun `Test callAllList response KO`() {
        /* Given */
        val callMock: Call<GeneralGetHttpModel> = mock()
        val generalGetHttpModelMock: GeneralGetHttpModel = mock()

        /* when */
        whenever(generalGetHttpModelMock.message).thenReturn(arrayListOf())
        whenever(apiInterface.getAllListDogs()).thenReturn(callMock)
        doAnswer { invocation ->
            val callback: Callback<GeneralGetHttpModel> =
                invocation.getArgument(0)
            callback.onFailure(callMock, throwable)
        }.whenever(callMock).enqueue(any<Callback<GeneralGetHttpModel>>())
        presenterTest.callAllList()

        /* Then */
        verify(view, times(2)).showLoading()
        verify(view, times(1)).showError()
    }

    @Test
    fun `Test callAllList response OK message null`() {
        /* Given */
        val callMock: Call<GeneralGetHttpModel> = mock()
        val generalGetHttpModelMock: GeneralGetHttpModel = mock()

        /* when */
        whenever(generalGetHttpModelMock.message).thenReturn(null)
        whenever(apiInterface.getAllListDogs()).thenReturn(callMock)
        doAnswer { invocation ->
            val callback: Callback<GeneralGetHttpModel> =
                invocation.getArgument(0)
            callback.onResponse(callMock, Response.success(generalGetHttpModelMock))
        }.whenever(callMock).enqueue(any<Callback<GeneralGetHttpModel>>())
        presenterTest.callAllList()

        /* Then */
        verify(view, times(2)).showLoading()
        verify(view, times(1)).showError()
    }

    @Test
    fun `Test callAllList response OK message not null`() {
        /* Given */
        val callMock: Call<GeneralGetHttpModel> = mock()
        val generalGetHttpModelMock: GeneralGetHttpModel = mock()
        val list = arrayListOf("galgo", "terrier")

        /* when */
        whenever(generalGetHttpModelMock.message).thenReturn(list)
        whenever(apiInterface.getAllListDogs()).thenReturn(callMock)
        doAnswer { invocation ->
            val callback: Callback<GeneralGetHttpModel> = invocation.getArgument(0)
            callback.onResponse(callMock, Response.success(generalGetHttpModelMock))
        }.whenever(callMock).enqueue(any<Callback<GeneralGetHttpModel>>())
        presenterTest.callAllList()

        /* Then */
        verify(view, times(2)).showLoading()
        verify(view, times(1)).addListAdapter(any())
        verify(view, times(1)).hidenLoading()
    }

    @Test
    fun `Test callSubBreed response KO`() {
        /* Given */
        val stringBreed = "terier"
        val callMock: Call<GeneralGetHttpModel> = mock()

        /* when */
        whenever(apiInterface.getSubBreeds("breed/$stringBreed/list")).thenReturn(callMock)
        doAnswer { invocation ->
            val callback: Callback<GeneralGetHttpModel> = invocation.getArgument(0)
            callback.onFailure(callMock, throwable)
        }.whenever(callMock).enqueue(any<Callback<GeneralGetHttpModel>>())
        presenterTest.callSubBreed(stringBreed)

        /* Then */
        verify(view, times(2)).showLoading()
        verify(view, times(1)).showError()
    }

    @Test
    fun `Test callSubBreed response OK body null`() {
        /* Given */
        val stringBreed = "terier"
        val callMock: Call<GeneralGetHttpModel> = mock()
        val generalGetHttpModelMock: GeneralGetHttpModel = mock()

        /* when */
        whenever(generalGetHttpModelMock.message).thenReturn(null)
        whenever(apiInterface.getSubBreeds("breed/$stringBreed/list")).thenReturn(callMock)
        doAnswer { invocation ->
            val callback: Callback<GeneralGetHttpModel> = invocation.getArgument(0)
            callback.onResponse(callMock, Response.success(null))
        }.whenever(callMock).enqueue(any<Callback<GeneralGetHttpModel>>())
        presenterTest.callSubBreed(stringBreed)

        /* Then */
        verify(view, times(2)).showLoading()
        verify(view, times(1)).showError()
    }

    @Test
    fun `Test callSubBreed response OK body not null`() {
        /* Given */
        val stringBreed = "terier"
        val callMock: Call<GeneralGetHttpModel> = mock()
        val generalGetHttpModelMock: GeneralGetHttpModel = mock()

        /* when */
        whenever(generalGetHttpModelMock.message).thenReturn(arrayListOf())
        whenever(apiInterface.getSubBreeds("breed/$stringBreed/list")).thenReturn(callMock)
        doAnswer { invocation ->
            val callback: Callback<GeneralGetHttpModel> = invocation.getArgument(0)
            callback.onResponse(callMock, Response.success(generalGetHttpModelMock))
        }.whenever(callMock).enqueue(any<Callback<GeneralGetHttpModel>>())
        presenterTest.callSubBreed(stringBreed)

        /* Then */
        verify(view, times(2)).showLoading()
        verify(view, times(1)).updateSublist(stringBreed, arrayListOf())
        verify(view, times(1)).hidenLoading()
    }
}
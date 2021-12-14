package com.example.exampletestingproyect.presenter.second

import com.example.exampletestingproyect.presenter.BaseTestPresenterTest
import com.example.exampletestingproyect.models.GeneralGetHttpModel
import com.example.exampletestingproyect.retrofit.APIInterface
import com.example.exampletestingproyect.view.fragment.secund.ISecondView
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SecondPresenterTest : BaseTestPresenterTest() {

    private lateinit var presenterTest: SecondPresenter
    private val view: ISecondView = mock()
    private val apiInterface: APIInterface = mock()

    @Before
    override fun setUp() {
        super.setUp()

        presenterTest = SecondPresenter()
        presenterTest.attactView(view)
        presenterTest.setPropertyValue("apiInterface", apiInterface)
    }

    /**
     * https://www.itdo.com/blog/que-es-bdd-behavior-driven-development/
     * BDD (Behavior Driven Development), es una estrategia de desarrollo dirigido por comportamiento,
     * que ha evolucionado desde TDD (Test Driven Development), aunque no se trata de una técnica de testing.
     *
     * Para definir los casos BDD para una historia de usuario se deben definir bajo el patrón ‘Given-When-Then’, que se define como:
    Given ‘dado’: Se especifica el escenario, las precondiciones.
    When ‘cuando’: Las condiciones de las acciones que se van a ejecutar.
    Then ‘entonces’: El resultado esperado, las validaciones a realizar.

    Un ejemplo práctico sería:
    Given: Dado que el usuario no ha introducido ningún dato en el formulario.
    When: Cuando hace clic en el botón Enviar.
    Then: Se deben mostrar los mensajes de validación apropiados.
     */
    @Test
    fun `Test callBreedImage response KO`() {
        /* Given */
        val nameString = "bulldog-boston"
        val callMock: Call<GeneralGetHttpModel> = mock()

        /* when */
        whenever(apiInterface.getImageFromDogs("breed/${nameString.split("-")[0]}/images")).thenReturn(callMock)
        doAnswer { invocation ->
            val callback: Callback<GeneralGetHttpModel> =
                invocation.getArgument(0)
            callback.onFailure(callMock, throwable)
        }.whenever(callMock).enqueue(any<Callback<GeneralGetHttpModel>>())
        presenterTest.callBreedImage(nameString)

        /* Then */
        verify(view, times(1)).showError()
    }

    @Test
    fun `Test callBreedImage response OK message null`() {
        /* Given */
        val nameString = "bulldog-boston"
        val callMock: Call<GeneralGetHttpModel> = mock()
        val generalGetHttpModelMock: GeneralGetHttpModel = mock()

        /* when */
        whenever(generalGetHttpModelMock.message).thenReturn(null)
        whenever(apiInterface.getImageFromDogs("breed/${nameString.split("-")[0]}/images")).thenReturn(callMock)
        doAnswer { invocation ->
            val callback: Callback<GeneralGetHttpModel> =
                invocation.getArgument(0)
            callback.onResponse(callMock, Response.success(generalGetHttpModelMock))
        }.whenever(callMock).enqueue(any<Callback<GeneralGetHttpModel>>())
        presenterTest.callBreedImage(nameString)

        /* Then */
        verify(view, times(1)).showError()
    }

    @Test
    fun `Test callBreedImage response OK message list empty`() {
        /* Given */
        val nameString = "bulldog-boston"
        val callMock: Call<GeneralGetHttpModel> = mock()
        val generalGetHttpModelMock: GeneralGetHttpModel = mock()

        /* when */
        whenever(generalGetHttpModelMock.message).thenReturn(arrayListOf())
        whenever(apiInterface.getImageFromDogs("breed/${nameString.split("-")[0]}/images")).thenReturn(callMock)
        doAnswer { invocation ->
            val callback: Callback<GeneralGetHttpModel> =
                invocation.getArgument(0)
            callback.onResponse(callMock, Response.success(generalGetHttpModelMock))
        }.whenever(callMock).enqueue(any<Callback<GeneralGetHttpModel>>())
        presenterTest.callBreedImage(nameString)

        /* Then */
        verify(view, times(1)).showError()
    }

    @Test
    fun `Test callBreedImage response OK message list not empty`() {
        /* Given */
        val nameString = "bulldog-boston"
        val callMock: Call<GeneralGetHttpModel> = mock()
        val generalGetHttpModelMock: GeneralGetHttpModel = mock()
        val urlImageString = "https://images.dog.ceo/breeds/bulldog-boston/n02096585_6238.jpg"

        /* when */
        whenever(generalGetHttpModelMock.message).thenReturn(arrayListOf(urlImageString))
        whenever(apiInterface.getImageFromDogs("breed/${nameString.split("-")[0]}/images")).thenReturn(callMock)
        doAnswer { invocation ->
            val callback: Callback<GeneralGetHttpModel> =
                invocation.getArgument(0)
            callback.onResponse(callMock, Response.success(generalGetHttpModelMock))
        }.whenever(callMock).enqueue(any<Callback<GeneralGetHttpModel>>())
        presenterTest.callBreedImage(nameString)

        /* Then */
        verify(view, times(1)).initImageView(any())
        verify(view, times(1)).hidenLoading()
    }

}
package com.example.exampletestingproyect

import com.nhaarman.mockitokotlin2.mock
import org.junit.After
import org.junit.Before
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit
import java.lang.reflect.Method

open class BaseTestPresenterTest {

    private val retrofitMock: Retrofit = mock()
    open val throwable: Throwable = mock()

    protected inline fun <reified T : Any> T.setPropertyValue(
        propertyName: String,
        propertyValue: Any?
    ) {
        this.javaClass.getDeclaredField(propertyName).apply { isAccessible = true }
            .set(this, propertyValue)
    }

    protected fun <T : Any> Any.getPropertyValue(propertyName: String): T =
        this.javaClass.getDeclaredField(propertyName).apply { isAccessible = true }.get(this) as T

    protected fun Any.accessMethod(methodName: String, vararg parameters: Class<*>?): Method {
        return this.javaClass.getDeclaredMethod(methodName, *parameters).apply {
            isAccessible = true
        }
    }

    protected fun Any.runMethod(methodName: String, vararg parameters: Any) =
        runMethod<Unit>(methodName, *parameters)

    protected fun <T> Any.runMethod(methodName: String, vararg parameters: Any): T =
        accessMethod(methodName, *parameters.map { it::class.java }.toTypedArray()).invoke(
            this,
            *parameters
        ) as T

    @Before
    open fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @After
    open fun tearDown() {
        Mockito.clearAllCaches()
    }
}
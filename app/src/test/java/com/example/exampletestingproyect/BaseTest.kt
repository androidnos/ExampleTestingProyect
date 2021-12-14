package com.example.exampletestingproyect

import java.lang.reflect.Method

/**
 * Class base de test para acceder a variables y metodos privados
 * también para invocar métodos privados
 */
open class BaseTest {

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
}
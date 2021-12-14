package com.example.exampletestingproyect.presenter

import com.example.exampletestingproyect.BaseTest
import com.nhaarman.mockitokotlin2.mock
import org.junit.After
import org.junit.Before
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

open class BaseTestPresenterTest: BaseTest() {

    open val throwable: Throwable = mock()

    @Before
    open fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @After
    open fun tearDown() {
        Mockito.clearAllCaches()
    }
}
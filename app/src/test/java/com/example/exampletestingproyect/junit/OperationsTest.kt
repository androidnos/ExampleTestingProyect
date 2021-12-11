package com.example.exampletestingproyect.junit

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class OperationsTest {

    @Test
    fun `Test call Operation class`() {
        /* When */
        val operations = Operations()

        /* Then */
        assertNotNull(operations)
    }

    @Test
    fun `Test addition`() {
        /* Given */
        val itemExpected = 30

        /* When */
        val itemReturn = Operations.addition(15, 15)

        /* Then */
        assertNotNull(itemReturn)
        assertEquals(itemExpected, itemReturn)
    }

    @Test
    fun `Test addition error`() {
        /* Given */
        val itemExpected = 30

        /* When */
        val itemReturn = Operations.addition(0, 15)

        /* Then */
        assertNotNull(itemReturn)
        assertNotEquals(itemExpected, itemReturn)
    }

    @Test
    fun `Test subtraction`() {
        /* Given */
        val itemExpected = 3

        /* When */
        val itemReturn = Operations.subtraction(6, 3)

        /* Then */
        assertNotNull(itemReturn)
        assert(itemExpected == itemReturn)
    }

    @Test
    fun `Test multiplication`() {
        /* Given */
        val itemExpected = 144

        /* When */
        val itemReturn = Operations.multiplication(12, 12)

        /* Then */
        assertNotNull(itemReturn)
        assert(itemExpected == itemReturn)
    }

    @Test
    fun `Test division`() {
        /* Given */
        val itemExpected = 1

        /* When */
        val itemReturn = Operations.division(12, 12)

        /* Then */
        assertNotNull(itemReturn)
        assert(itemExpected == itemReturn)
    }

    @Test
    fun `Test division num2 = 0`() {
        /* Given */
        val itemExpected = "Infinito"

        /* When */
        val itemReturn = Operations.division(12, 0)

        /* Then */
        assertNotNull(itemReturn)
        assert(itemExpected == itemReturn)
    }

    @Test
    fun `Test division num1 and num2 = 0`() {
        /* Given */
        val itemExpected = "Error"

        /* When */
        val itemReturn = Operations.division(0, 0)

        /* Then */
        assertNotNull(itemReturn)
        assert(itemExpected == itemReturn)
    }
}
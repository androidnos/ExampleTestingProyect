package com.example.exampletestingproyect.junit

import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Ignore
import org.junit.Test

class GeneratePhrasesTest {

    private lateinit var generatePhrasesTest: GeneratePhrases

    /**
     * Esta función se va a invocar cada vez que se ejecute un test, siempre antes de comenzar el test
     */
    @Before
    fun setUp(){
        println("@Before -> fun setUp()")
        generatePhrasesTest = GeneratePhrases()
    }

    /**
     * Test con el type1 comprobando que lo que devuelve el metodo a testear es lo mismo que lo que esperamos
     */
    @Test
    fun `Test getPhrases Type1`() {
        /* Given */
        val itemExpected = "La risa no es un mal comienzo para la amistad. Y está lejos de ser un mal final."

        /* When */
        val itemReturn = generatePhrasesTest.getPhrases(GeneratePhrases.TYPE1)

        /* Then */
        assert(itemReturn == itemExpected)
        assertNotNull(itemReturn)
        assertFalse(itemReturn.isNullOrEmpty())
        assertTrue(!itemReturn.isNullOrEmpty())

        println("@Test -> fun `Test getPhrases Type1`()")
    }

    /**
     * Test con el type2 comprobando que lo que devuelve el metodo a testear es lo mismo que lo que esperamos
     */
    @Test
    fun `Test getPhrases Type2`() {
        /* Given */
        val itemExpected = "El que vive pruedentemente vive tristemente."

        /* When */
        val itemReturn = generatePhrasesTest.getPhrases(GeneratePhrases.TYPE2)

        /* Then */
        assert(itemReturn == itemExpected)

        println("@Test ->  fun `Test getPhrases Type2`()")
    }

    /**
     * Test con el type3 comprobando que lo que devuelve el metodo a testear es lo mismo que lo que esperamos
     */
    @Test
    fun `Test getPhrases Type3`() {
        /* Given */
        val itemExpected = "Hay un libro abierto siempre para todos los ojos: la naturaleza."

        /* When */
        val itemReturn = generatePhrasesTest.getPhrases(GeneratePhrases.TYPE3)

        /* Then */
        assert(itemReturn == itemExpected)

        println("@Test ->  fun `Test getPhrases Type3`()")
    }

    /**
     * Test con el null comprobando que lo que devuelve el metodo a testear es lo mismo que lo que esperamos
     */
    @Test
    fun `Test getPhrases null`() {
        /* Given */
        val itemExpected: String? = null

        /* When */
        val itemReturn = generatePhrasesTest.getPhrases(null)

        /* Then */
        assertEquals(itemReturn, itemExpected)

        println("@Test ->  fun `Test getPhrases null`()")
    }

    /**
     * Test con el null comprobando que lo que devuelve el metodo a testear es lo mismo que lo que esperamos
     */
    @Test
    fun `Test getPhrases else`() {
        /* Given */
        val itemExpected = "Lo malo no es vivir en las nubes, sino bajar"

        /* When */
        val itemReturn = generatePhrasesTest.getPhrases("else")

        /* Then */
        assertEquals(itemReturn, itemExpected)

        println("@Test ->  fun `Test getPhrases else`()")
    }

    /**
     * En este test vamos a forzar un error ya que lo que devuelve el metodo a testear y lo que se comprueba no es lo mismo
     */
    @Test
    fun `Test getPhrases Type1 error`() {
        /* Given */
        val itemExpected = "El que vive pruedentemente vive tristemente."

        /* When */
        val itemReturn = generatePhrasesTest.getPhrases(GeneratePhrases.TYPE1)

        /* Then */
        assert(itemReturn == itemExpected)

        println("@Test ->  fun `Test getPhrases Type1 error`()")
    }

    /**
     * En este test vamos a ignorar el test, por lo tanto aunque falle no mostrar el error
     */
    @Ignore
    @Test
    fun `Test getPhrases Type1 error ignore`() {
        /* Given */
        val itemExpected = "El que vive pruedentemente vive tristemente."

        /* When */
        val itemReturn = generatePhrasesTest.getPhrases(GeneratePhrases.TYPE1)

        /* Then */
        assert(itemReturn == itemExpected)

        println(" @Ignore@Test ->  fun `Test getPhrases Type1 error ignore`()")
    }

    @After
    fun tearDown(){
        println("@After ->  fun tearDown()")
    }

}
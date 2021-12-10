package com.example.exampletestingproyect.junit

class GeneratePhrases {

    companion object {
        const val TYPE1 = "RISA"
        const val TYPE2 = "TRISTEZA"
        const val TYPE3 = "NATURALEZA"
    }

    fun getPhrases(type: String?): String? {
        return when (type) {
            TYPE1 -> "La risa no es un mal comienzo para la amistad. Y está lejos de ser un mal final."
            TYPE2 -> "El que vive pruedentemente vive tristemente."
            TYPE3 -> "Hay un libro abierto siempre para todos los ojos: la naturaleza."
            null -> null
            else -> "Lo malo no es vivir en las nubes, sino bajar"
        }
    }
}
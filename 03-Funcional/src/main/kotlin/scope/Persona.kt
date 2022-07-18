package scope

import java.util.*

data class Persona(
    var nombre: String = "",
    var edad: Int = 0,
) {
    val uuid = UUID.randomUUID()

    fun saludar() {
        println("Hola, me llamo $nombre y tengo $edad años")
    }
}
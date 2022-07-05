package models

import java.time.LocalDateTime

class Persona {
    val nombre: String = "Pepe"

    var edad: Int = 18
        get() = field * 2
        set(value) {
            if (value < 0) {
                // throw IllegalArgumentException("No puedes tener menos de 0 años")
                field = 0
            } else {
                field = value
            }
        }

    private val createdAt: LocalDateTime = LocalDateTime.now()


    fun show(): String {
        return "Nombre: $nombre, Edad: $edad, CreatedAt: $createdAt"
    }
}
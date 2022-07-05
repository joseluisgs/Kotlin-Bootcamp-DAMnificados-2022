package models

import java.time.LocalDateTime

class Persona {
    val nombre: String = "Pepe"
    var edad: Int = 18
    private val createdAt: LocalDateTime = LocalDateTime.now()

    fun show(): String {
        return "Nombre: $nombre, Edad: $edad, CreatedAt: $createdAt"
    }
}
package models

import java.time.LocalDateTime

class Persona {
    var nombre: String = ""

    var edad: Int = 0
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


    constructor(nombre: String = "Pepe", edad: Int = 18) {
        this.nombre = nombre
        this.edad = edad
    }

    init {
        println("Se ha inicializado una persona")
    }


    fun show(): String {
        return "Nombre: $nombre, Edad: $edad, CreatedAt: $createdAt"
    }

}
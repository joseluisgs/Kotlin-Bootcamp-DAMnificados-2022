package models

import java.time.LocalDateTime

class Persona(var nombre: String = "Pepe", var edad: Int = 0) {
    val isMayorDeEdad: Boolean = edad >= 18
    // get() = edad >= 18

    /*var edad: Int = 0
        get() = field * 2
        set(value) {
            if (value < 0) {
                // throw IllegalArgumentException("No puedes tener menos de 0 años")
                field = 0
            } else {
                field = value
            }
        }*/

    @JvmName("getEdadDoble")
    fun getEdad(): Int {
        return edad * 2
    }

    @JvmName("setEdadLimitacion")
    fun setEdad(value: Int) {
        if (value < 0) {
            edad = 0
        } else {
            edad = value
        }
    }

    private val createdAt: LocalDateTime = LocalDateTime.now()


    init {
        println("Se ha inicializado una persona")
    }


    fun show(): String {
        return "Nombre: $nombre, Edad: $edad, CreatedAt: $createdAt"
    }

}
package models

import java.util.*

// POKO for the user model = Plain Old Kotlin Object
data class Ordenador(val uuid: UUID = UUID.randomUUID(), var marca: String, var precio: Double = 99.9) {
    val mySize: Int = size

    companion object {
        var size = 2000

        // Factory method
        fun create(marca: String, precio: Double): Ordenador {
            size++
            return Ordenador(marca = marca, precio = precio)
        }
    }
}
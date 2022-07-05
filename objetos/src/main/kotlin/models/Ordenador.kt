package models

import java.time.LocalDateTime
import java.util.*
import kotlin.properties.Delegates

// POKO for the user model = Plain Old Kotlin Object
data class Ordenador(val uuid: UUID = UUID.randomUUID(), var marca: String, var precio: Double = 99.9) {
    val mySize: Int = size

    var precioActual by Delegates.observable(precio) { prop, old, new ->
        println("Reactividad en ${prop.name}: $old -> $new")
    }

    // val fechaVenta = LocalDateTime.now()
    val fechaVenta by lazy {
        println("Calculando fecha de venta")
        LocalDateTime.now()
    }

    companion object {
        var size = 2000

        // Factory method
        fun create(marca: String, precio: Double): Ordenador {
            size++
            return Ordenador(marca = marca, precio = precio)
        }
    }
}
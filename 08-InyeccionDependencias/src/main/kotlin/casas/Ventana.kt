package koin.casas

import java.util.*

data class Ventana(private val id: UUID = UUID.randomUUID()) {
    private var isOpened: Boolean = false
    fun abrir() {
        println("Abriendo ventana")
        isOpened = true
    }

    fun cerrar() {
        println("Cerrando ventana")
        isOpened = false
    }
}
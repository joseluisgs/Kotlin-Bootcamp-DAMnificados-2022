package koin.cafeteras

import java.util.*

class CalentadorElectrico : Calentador {
    private val id: UUID = UUID.randomUUID()

    // true si esta calentando, false si esta apagado
    private var calentando = false

    override fun encender() {
        calentando = true
        println("~ ~ calentando ~ ~ ~")
    }

    override fun apagar() {
        calentando = false
    }

    override fun estaCaliente(): Boolean {
        return calentando
    }
}

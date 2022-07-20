package anotaciones.component

import org.koin.core.annotation.Single

@Single
class ElectricHeater : Heater {

    private var calentando: Boolean = false

    override fun encender() {
        println("~ ~ ~ calentando ~ ~ ~")
        calentando = true
    }

    override fun apagar() {
        calentando = false
    }

    override fun estaCaliente(): Boolean = calentando
}
package anotaciones.component

import org.koin.core.annotation.Single

@Single
class CoffeeMaker(private val pump: Pump, private val heater: Heater) {

    fun brew() {
        heater.encender()
        pump.bombear()
        println("[_]P !Taza de Café! [_]P ")
        heater.apagar()
    }
}
package anotaciones.component

import org.koin.core.annotation.Single

@Single
class Thermosiphon(private val heater: Heater) : Pump {
    override fun bombear() {
        if (heater.estaCaliente()) {
            println("=> => bombeando => =>")
        }
    }
}
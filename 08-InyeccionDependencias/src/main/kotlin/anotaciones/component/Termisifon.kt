package anotaciones.component

import org.koin.core.annotation.Single

@Single
class Termisifon(private val calentador: Calentador) : Bomba {
    override fun bombear() {
        if (calentador.estaCaliente()) {
            println("=> => bombeando => =>")
        }
    }
}
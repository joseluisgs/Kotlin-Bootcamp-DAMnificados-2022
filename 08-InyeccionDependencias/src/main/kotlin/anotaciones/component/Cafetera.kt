package anotaciones.component

import org.koin.core.annotation.Single

@Single
data class Cafetera(private val bomba: Bomba, private val calentador: Calentador) {

    fun servir() {
        calentador.encender()
        bomba.bombear()
        println("[_]P !Taza de Café! [_]P ")
        calentador.apagar()
    }
}
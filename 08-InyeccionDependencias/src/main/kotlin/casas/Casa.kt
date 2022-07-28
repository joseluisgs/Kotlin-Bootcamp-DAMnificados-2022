package koin.casas

import java.util.*

data class Casa(
    private val ventana: Ventana,
    private val puerta: Puerta,
) {
    val id: UUID = UUID.randomUUID()

    fun entrar() {
        puerta.abrir()
        puerta.cerrar()
    }

    fun ventilar() {
        ventana.abrir()
    }

    override fun toString(): String {
        return "Casa(id=$id, ventana=$ventana, puerta=$puerta)"
    }
}

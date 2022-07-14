package models

import java.util.*

data class Proceso(
    val id: UUID = UUID.randomUUID(),
    val nombre: String,
    var prioridad: Int = 1,
    var estado: Estado = Estado.PENDIENTE
) {

    init {
        when {
            prioridad < 1 -> prioridad = 1
            prioridad > 9 -> prioridad = 9
        }
    }

    enum class Estado {
        EN_EJECUCION,
        TERMINADO,
        BLOQUEADO,
        SUSPENDIDO,
        PENDIENTE
    }
}
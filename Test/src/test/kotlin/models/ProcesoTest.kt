package models

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class ProcesoTest {
    @Test
    fun procesoTieneProridadAdecuada() {
        val prioridades = listOf(0, 1, 5, 9, 10, 11)
        prioridades.forEach {
            val proceso = Proceso(
                nombre = "Proceso $it",
                prioridad = it
            )
            println(proceso.prioridad)
            assertTrue(proceso.prioridad in (1..10))
        }
    }
}
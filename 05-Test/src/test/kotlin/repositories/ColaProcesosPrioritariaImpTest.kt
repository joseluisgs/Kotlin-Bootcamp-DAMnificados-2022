package repositories

import models.Proceso
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals


internal class ColaProcesosPrioritariaImpTest {
    private lateinit var cola: ColaProcesosPrioritaria

    @BeforeEach
    fun setUp() {
        cola = ColaProcesosPrioritariaImp()
    }

    @Test
    fun size() {
        assertTrue(cola.size == 0)
    }

    @Test
    fun isEmpty() {
        assertTrue(cola.isEmpty())
    }

    @Test
    fun push() {
        val proceso = Proceso(nombre = "Proceso Test")
        val res = cola.push(proceso)
        assertAll(
            { assertFalse(cola.isEmpty()) },
            { assertTrue(cola.size == 1) },
            { assertTrue(res) }
        )
    }

    @Test
    fun pushRepetidos() {
        val proceso = Proceso(
            id = UUID.randomUUID(),
            nombre = "Proceso Test"
        )
        val res1 = cola.push(proceso)
        val res2 = cola.push(proceso)
        assertAll(
            { assertFalse(cola.isEmpty(), "Cola no es vacía") },
            { assertTrue(cola.size == 1, "El tamaño es 1") },
            { assertTrue(res1, "Inserta el proceso") },
            { assertFalse(res2, "No debería insertar el proceso") }
        )
    }

    @Test
    fun pop() {
        val proceso = Proceso(nombre = "Proceso Test")
        cola.push(proceso)
        val res = cola.pop()
        assertEquals(res, proceso, "Los procesos son iguales")
    }

    @Test
    fun popVarios() {
        val p1 = Proceso(nombre = "P1")
        val p2 = Proceso(nombre = "P2")
        cola.push(p1)
        cola.push(p2)
        cola.pop()
        val res = cola.size
        assertEquals(1, res)
    }

    @Test
    fun popVacia() {
        val res = cola.pop()
        assertEquals(null, res, "La cola es vacía")
    }

    @Test
    fun popPorOrden() {
        val p1 = Proceso(nombre = "P1", prioridad = 1)
        val p2 = Proceso(nombre = "P2", prioridad = 5) // Debe devolver p2
        cola.push(p1)
        cola.push(p2)
        val res = cola.pop()
        assertAll(
            { assertEquals(res, p2, "Se devuleve P2") },
            { assertNotEquals(res, p1, "No se devuleve p1") }
        )
    }

    @Test
    fun getAll() {
        val p1 = Proceso(nombre = "P1", prioridad = 1)
        val p2 = Proceso(nombre = "P2", prioridad = 5)
        cola.push(p1)
        cola.push(p2)

        val res = cola.getAll()

        assertAll(
            { assertFalse(cola.isEmpty()) },
            { assertEquals(cola.size, 2) },
            { assertTrue(res[0].prioridad >= res[1].prioridad, "Salen por prioridad") }
        )
    }

    @Test
    fun getAllVacia() {
        assertTrue(cola.getAll().isEmpty(), "La cola es Vacía")
    }

    @Test
    fun getById() {
        val proceso = Proceso(nombre = "Proceso Test")
        cola.push(proceso)
        val res = cola.getById(proceso.id)
        assertEquals(res, proceso, "Es el proceso buscado")
    }

    @Test
    fun getByIdNoExiste() {
        val proceso = Proceso(nombre = "Proceso Test")
        cola.push(proceso)
        val res = cola.getById(UUID.randomUUID())
        assertEquals(res, null, "Es el no existe")
    }


}
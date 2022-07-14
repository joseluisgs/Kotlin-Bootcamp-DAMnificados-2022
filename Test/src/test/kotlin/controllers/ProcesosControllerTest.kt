package controllers

import Exceptions.ProcesoException
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import models.Proceso
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import repositories.ColaProcesosPrioritaria
import java.util.*

@ExtendWith(MockKExtension::class)
internal class ProcesosControllerTest {
    @MockK
    lateinit var cola: ColaProcesosPrioritaria

    @InjectMockKs
    private lateinit var controller: ProcesosController

    init {
        MockKAnnotations.init(this)
    }

    @Test
    fun push() {
        // Given
        val p1 = Proceso(nombre = "p1")
        every { cola.push(p1) } returns true

        // Then
        val res = controller.push(p1)

        // Assertions
        assertTrue(res)
        verify(exactly = 1) { cola.push(p1) }
    }

    @Test
    fun pushRepetidos() {
        // Given
        val p1 = Proceso(
            id = UUID.randomUUID(),
            nombre = "p1"
        )
        val p2 = p1.copy(nombre = "p2")

        every { cola.push(p1) } returns true
        every { cola.push(p2) } returns false

        // Then
        val res1 = controller.push(p1)
        val res2 = controller.push(p2)

        // Assertions
        assertAll(
            { assertTrue(res1) },
            { assertFalse(res2) }
        )
        verify(exactly = 1) { cola.push(p1) }
        verify(exactly = 1) { cola.push(p2) }
    }

    @Test
    fun pop() {
        val p1 = Proceso(nombre = "p1")
        every { cola.pop() } returns p1
        val res = controller.pop()

        assertEquals(p1, res)

        verify(exactly = 1) { cola.pop() }
    }

    @Test
    fun popVacio() {
        every { cola.pop() } returns null

        val ex = assertThrows<ProcesoException> {
            controller.pop()
        }

        assertEquals(ex.message, "La cola está vacía")
    }

    @Test
    fun getById() {
        val p1 = Proceso(nombre = "p1")
        every { cola.getById(p1.id) } returns p1

        val res = controller.get(p1.id)

        assertEquals(p1, res)

        verify(exactly = 1) { cola.getById(p1.id) }
    }

    @Test
    fun getByIdNoExiste() {
        val id = UUID.randomUUID()
        every { cola.getById(id) } returns null

        val ex = assertThrows<ProcesoException> {
            controller.get(id)
        }

        assertEquals(ex.message, "No existe proceso con id $id")

        verify(exactly = 1) { cola.getById(id) }
    }

    @Test
    fun getAll() {
        val list = listOf(Proceso(nombre = "p2", prioridad = 5), Proceso(nombre = "p1", prioridad = 1))

        every { cola.getAll() } returns list

        val res = controller.getAll()

        assertAll(
            { assertEquals(list, res) },
            { assertTrue(res[0].prioridad >= res[1].prioridad) }
        )

        verify(exactly = 1) { cola.getAll() }
    }

    @Test
    fun isEmpty() {
        every { cola.isEmpty() } returns true
        val res = controller.isVacia()
        assertTrue(res)
        verify(exactly = 1) { cola.isEmpty() }
    }

    @Test
    fun size() {
        every { cola.size } returns 1
        val res = controller.size()
        assertEquals(1, res)
        verify(exactly = 1) { cola.size }
    }
}
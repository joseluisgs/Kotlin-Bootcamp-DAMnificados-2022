package controllers

import exceptions.RaquetaException
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import models.Raqueta
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import repositories.raquetas.RaquetasRepository
import services.raquetas.StorageRaquetasCsvService
import java.io.File
import java.util.*

/**
 * Creamos un mock de las dependencias para poder testear el controlador
 */
@ExtendWith(MockKExtension::class)
internal class RaquetasControllerTest {

    @MockK
    lateinit var raquetasRepository: RaquetasRepository

    @MockK
    lateinit var storageRaquetasCsvService: StorageRaquetasCsvService

    @InjectMockKs
    lateinit var raquetasController: RaquetasController

    private val raqueta = Raqueta(UUID.randomUUID(), "Marca Test", "Modelo Test", 200.0, 300)

    init {
        MockKAnnotations.init(this)
    }

    @Test
    fun importDataFromCsv() {
        every { storageRaquetasCsvService.loadFromFile(File("data.csv")) } returns listOf(raqueta)
        every { raquetasRepository.save(raqueta) } returns raqueta

        raquetasController.importDataFromCsv(File("data.csv"))

        verify(exactly = 1) { storageRaquetasCsvService.loadFromFile(File("data.csv")) }
        verify(exactly = 1) { raquetasRepository.save(raqueta) }
    }

    @Test
    fun getAll() {
        every { raquetasRepository.findAll() } returns listOf(raqueta)

        val res = raquetasController.getAll()

        assert(res == listOf(raqueta))

        verify(exactly = 1) { raquetasRepository.findAll() }
    }

    @Test
    fun getById() {
        every { raquetasRepository.findById(raqueta.id) } returns raqueta

        val res = raquetasController.getById(raqueta.id)

        assert(res == raqueta)

        verify(exactly = 1) { raquetasRepository.findById(raqueta.id) }
    }

    @Test
    fun getByIdNoExiste() {
        every { raquetasRepository.findById(raqueta.id) } returns null

        val res = assertThrows<RaquetaException> { raquetasController.getById(raqueta.id) }

        assert(res.message == "Raqueta with id ${raqueta.id} not found")

        verify(exactly = 1) { raquetasRepository.findById(raqueta.id) }
    }

    @Test
    fun save() {
        every { raquetasRepository.save(raqueta) } returns raqueta

        val res = raquetasController.save(raqueta)

        assert(res == raqueta)

        verify(exactly = 1) { raquetasRepository.save(raqueta) }
    }

    @Test
    fun delete() {
        every { raquetasRepository.delete(raqueta) } returns true

        val res = raquetasController.delete(raqueta)

        assert(res == raqueta)

        verify(exactly = 1) { raquetasRepository.delete(raqueta) }
    }

    @Test
    fun deleteNoExiste() {
        every { raquetasRepository.delete(raqueta) } returns false

        val res = assertThrows<RaquetaException> { raquetasController.delete(raqueta) }

        assert(res.message == "Raqueta with id ${raqueta.id} not found")

        verify(exactly = 1) { raquetasRepository.delete(raqueta) }
    }

    @Test
    fun getByMarcaAndModelo() {
        every { raquetasRepository.findAll() } returns listOf(raqueta)

        val res = raquetasController.getByMarcaAndModelo(raqueta.marca, raqueta.modelo)

        assert(res == raqueta)

        verify(exactly = 1) { raquetasRepository.findAll() }
    }

    @Test
    fun report() {
        every { raquetasRepository.findAll() } returns listOf(raqueta)

        val res = raquetasController.report()

        assertAll(
            { assert(res.contains("Informe de raquetas")) },
            { assert(res.contains("Total: 1")) },
            { assert(res.contains("cara: $raqueta")) },
            { assert(res.contains("pesada: $raqueta")) },
            { assert(res.contains("medio: ${raqueta.peso}")) },
            { assert(res.contains("marca:")) }
        )

        verify(exactly = 1) { raquetasRepository.findAll() }
    }
}
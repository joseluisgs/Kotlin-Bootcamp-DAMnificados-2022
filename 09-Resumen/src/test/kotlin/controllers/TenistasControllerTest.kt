package controllers

import exceptions.TenistaException
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import models.Raqueta
import models.Tenista
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import repositories.tenistas.TenistasRepository
import services.tenistas.StorageTenistasCsvService
import services.tenistas.StorageTenistasJsonService
import java.io.File
import java.time.LocalDate
import java.util.*

@ExtendWith(MockKExtension::class)
internal class TenistasControllerTest {

    @MockK
    lateinit var tenistasRepository: TenistasRepository

    @MockK
    lateinit var storageTenistasCsvService: StorageTenistasCsvService

    @MockK
    lateinit var storageTenistasJsonService: StorageTenistasJsonService

    @InjectMockKs
    lateinit var tenistasController: TenistasController

    private val tenista = Tenista(
        nombre = "Tenista Test",
        ranking = 3,
        fechaNacimiento = LocalDate.parse("1985-06-04"),
        añoProfesional = 2005,
        altura = 185,
        peso = 80,
        ganancias = 10000000.0,
        manoDominante = Tenista.ManoDominante.from("Izquierda"),
        tipoReves = Tenista.TipoReves.from("dos manos"),
        puntos = 6789,
        raqueta = Raqueta(UUID.randomUUID(), "Marca Test", "Modelo Test", 200.0, 300)
    )

    init {
        MockKAnnotations.init(this)
    }

    @Test
    fun importDataFromCsv() {
        every { storageTenistasCsvService.loadFromFile(File("data.csv")) } returns listOf(tenista)
        every { tenistasRepository.save(tenista) } returns tenista

        tenistasController.importDataFromCsv(File("data.csv"))

        verify(exactly = 1) { storageTenistasCsvService.loadFromFile(File("data.csv")) }
        verify(exactly = 1) { tenistasRepository.save(tenista) }
    }

    @Test
    fun getAll() {
        every { tenistasRepository.findAll() } returns listOf(tenista)

        val res = tenistasController.getAll()

        assert(res == listOf(tenista))

        verify(exactly = 1) { tenistasRepository.findAll() }
    }

    @Test
    fun getById() {
        every { tenistasRepository.findById(tenista.id) } returns tenista

        val res = tenistasController.getById(tenista.id)

        assert(res == tenista)

        verify(exactly = 1) { tenistasRepository.findById(tenista.id) }
    }

    @Test
    fun getByIdNoExiste() {
        every { tenistasRepository.findById(tenista.id) } returns null

        val res = assertThrows<TenistaException> { tenistasController.getById(tenista.id) }

        assert(res.message == "Tenista with id ${tenista.id} not found")

        verify(exactly = 1) { tenistasRepository.findById(tenista.id) }
    }

    @Test
    fun save() {
        every { tenistasRepository.save(tenista) } returns tenista

        val res = tenistasController.save(tenista)

        assert(res == tenista)

        verify(exactly = 1) { tenistasRepository.save(tenista) }
    }

    @Test
    fun delete() {
        every { tenistasRepository.delete(tenista) } returns true

        val res = tenistasController.delete(tenista)

        assert(res == tenista)

        verify(exactly = 1) { tenistasRepository.delete(tenista) }
    }

    @Test
    fun deleteNoExiste() {
        every { tenistasRepository.delete(tenista) } returns false

        val res = assertThrows<TenistaException> { tenistasController.delete(tenista) }

        assert(res.message == "Tenista with id ${tenista.id} not found")

        verify(exactly = 1) { tenistasRepository.delete(tenista) }
    }

    @Test
    fun exportDataToJson() {
        every { tenistasRepository.findAll() } returns listOf(tenista)
        every { storageTenistasJsonService.saveToFile(File("data.json"), listOf(tenista)) } just runs

        tenistasController.exportDataToJson(File("data.json"))

        verify(exactly = 1) { tenistasRepository.findAll() }
        verify(exactly = 1) { storageTenistasJsonService.saveToFile(File("data.json"), listOf(tenista)) }
    }

    @Test
    fun getByName() {
        every { tenistasRepository.findAll() } returns listOf(tenista)

        val res = tenistasController.getByName(tenista.nombre)

        assert(res == listOf(tenista))

        verify(exactly = 1) { tenistasRepository.findAll() }
    }

    @Test
    fun getByMarcaRaqueta() {
        every { tenistasRepository.findAll() } returns listOf(tenista)

        val res = tenistasController.getByMarcaRaqueta(tenista.raqueta!!.marca)

        assert(res == listOf(tenista))

        verify(exactly = 1) { tenistasRepository.findAll() }
    }

    @Test
    fun report() {
        every { tenistasRepository.findAll() } returns listOf(tenista)

        val res = tenistasController.report()

        assertAll(
            { assert(res.contains("Informe de tenistas")) },
            { assert(res.contains("Total: 1")) },
            { assert(res.contains("puntuación:")) },
            { assert(res.contains("altura:")) },
            { assert(res.contains("Reves:")) },
            { assert(res.contains("Raqueta:")) },
            { assert(res.contains("más usada:")) },
            { assert(res.contains("zurdos:")) },
            { assert(res.contains("media:")) },
            { assert(res.contains("joven:")) },
            { assert(res.contains("30 años:")) },
        )

        verify(exactly = 1) { tenistasRepository.findAll() }
    }
}
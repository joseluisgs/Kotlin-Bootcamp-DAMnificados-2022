package repositories.tenistas

import config.AppConfig
import config.DataBase
import entities.RaquetaDao
import entities.TenistaDao
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import models.Tenista
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.sql.SizedCollection
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import java.time.LocalDate

/**
 * No hace falta llamar los objetos o dependencias DAO directamente
 * Podemos usar los mocks para hacer pruebas
 * ahorrando el tiempo, solo nos hace falta simular su comportamiento
 */

@ExtendWith(MockKExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Para el beforeAll y afterAll
internal class TenistasRepositoryImplTestMock {

    @MockK
    lateinit var tenistaDao: UUIDEntityClass<TenistaDao>

    @MockK
    lateinit var raquetaDao: UUIDEntityClass<RaquetaDao>

    @InjectMockKs
    lateinit var tenistasRepository: TenistasRepositoryImpl

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
        puntos = 6789
    )

    // Un item para hacer las cosas más fáciles
    private lateinit var daoItem: TenistaDao

    init {
        MockKAnnotations.init(this)
    }

    @BeforeAll
    fun setUp() {
        DataBase.init(AppConfig.DEFAULT)
    }

    @AfterAll
    fun tearDown() {
        DataBase.dropTables()
    }

    @BeforeEach
    fun beforeEach() {
        DataBase.clearTables()
        transaction {
            daoItem = TenistaDao.new(tenista.id) {
                nombre = tenista.nombre
                ranking = tenista.ranking
                fechaNacimiento = tenista.fechaNacimiento
                añoProfesional = tenista.añoProfesional
                altura = tenista.altura
                peso = tenista.peso
                ganancias = tenista.ganancias
                manoDominante = tenista.manoDominante.mano
                tipoReves = tenista.tipoReves.tipo
                puntos = tenista.puntos
            }
        }
    }


    @Test
    fun findAll() {
        every { tenistaDao.all() } returns SizedCollection(listOf(daoItem))

        val res = tenistasRepository.findAll()

        assertAll(
            { assert(1 == res.size) },
            { assert(res[0] == tenista) }
        )

        verify { tenistaDao.all() }
    }

    @Test
    fun findById() {
        every { tenistaDao.findById(tenista.id) } returns daoItem

        val res = tenistasRepository.findById(tenista.id)

        assert(res == tenista)

        verify { tenistaDao.findById(tenista.id) }
    }

    @Test
    fun findByIdNoExiste() {
        every { tenistaDao.findById(tenista.id) } returns null

        val res = tenistasRepository.findById(tenista.id)

        assert(res == null)

        verify { tenistaDao.findById(tenista.id) }
    }

    @Test
    fun save() {
        every { tenistaDao.findById(tenista.id) } returns daoItem

        val res = tenistasRepository.save(tenista)

        assert(res == tenista)

        verify { tenistaDao.findById(tenista.id) }
    }

    @Test
    fun delete() {
        every { tenistaDao.findById(tenista.id) } returns daoItem

        val res = tenistasRepository.delete(tenista)

        assert(res)

        verify { tenistaDao.findById(tenista.id) }
    }

    @Test
    fun deleteNoExiste() {
        every { tenistaDao.findById(tenista.id) } returns null

        val res = tenistasRepository.delete(tenista)

        assert(!res)

        verify { tenistaDao.findById(tenista.id) }
    }
}
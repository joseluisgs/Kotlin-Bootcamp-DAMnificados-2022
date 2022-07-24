package repositories.raquetas

import config.AppConfig
import config.DataBase
import entities.RaquetaDao
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import models.Raqueta
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.sql.SizedCollection
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*

/**
 * No hace falta llamar los objetos o dependencias DAO directamente
 * Podemos usar los mocks para hacer pruebas
 * ahorrando el tiempo, solo nos hace falta simular su comportamiento
 */

@ExtendWith(MockKExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Para el beforeAll y afterAll
internal class RaquetasRepositoryImplTestMock {

    @MockK
    lateinit var raquetaDao: UUIDEntityClass<RaquetaDao>

    @InjectMockKs
    lateinit var raquetasRepository: RaquetasRepositoryImpl

    private val raqueta = Raqueta(UUID.randomUUID(), "Marca Test", "Modelo Test", 200.0, 300)

    // Un item para hacer las cosas más fáciles
    private lateinit var daoItem: RaquetaDao

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
        // Al menos vamos a tener un DAO o item para probar
        transaction {
            daoItem = RaquetaDao.new(raqueta.id) {
                marca = raqueta.marca
                modelo = raqueta.modelo
                precio = raqueta.precio
                peso = raqueta.peso
            }
        }
    }

    @Test
    fun findAll() {
        every { raquetaDao.all() } returns SizedCollection(listOf(daoItem))

        val res = raquetasRepository.findAll()

        assertAll(
            { assert(1 == res.size) },
            { assert(res[0] == raqueta) }
        )

        verify { raquetaDao.all() }
    }

    @Test
    fun findById() {
        every { raquetaDao.findById(raqueta.id) } returns daoItem

        val res = raquetasRepository.findById(raqueta.id)

        assert(res == raqueta)

        verify { raquetaDao.findById(raqueta.id) }
    }


    @Test
    fun findByIdNoExiste() {
        every { raquetaDao.findById(raqueta.id) } returns null

        val res = raquetasRepository.findById(raqueta.id)

        assert(res == null)

        verify { raquetaDao.findById(raqueta.id) }
    }


    @Test
    fun save() {
        every { raquetaDao.findById(raqueta.id) } returns daoItem

        val res = raquetasRepository.save(raqueta)

        assert(res == raqueta)

        verify { raquetaDao.findById(raqueta.id) }
    }

    @Test
    fun delete() {
        every { raquetaDao.findById(raqueta.id) } returns daoItem

        val res = raquetasRepository.delete(raqueta)

        assert(res)

        verify { raquetaDao.findById(raqueta.id) }
    }

    @Test
    fun deleteNoExiste() {
        every { raquetaDao.findById(raqueta.id) } returns null

        val res = raquetasRepository.delete(raqueta)

        assert(!res)

        verify { raquetaDao.findById(raqueta.id) }
    }
}
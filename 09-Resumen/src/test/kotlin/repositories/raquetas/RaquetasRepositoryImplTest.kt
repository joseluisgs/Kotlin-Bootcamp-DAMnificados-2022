package repositories.raquetas

import config.AppConfig
import config.DataBase
import entities.RaquetaDao
import models.Raqueta
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.*
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Para el beforeAll y afterAll
internal class RaquetasRepositoryImplTest {

    private var raquetasRepository: RaquetasRepository = RaquetasRepositoryImpl(RaquetaDao)

    private val raqueta = Raqueta(UUID.randomUUID(), "Marca Test", "Modelo Test", 200.0, 300)


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
    }

    @Test
    fun findAll() {
        val res = raquetasRepository.findAll()

        assert(res.isEmpty())
    }

    @Test
    fun findById() = transaction {
        val insert = RaquetaDao.new(raqueta.id) {
            marca = raqueta.marca
            modelo = raqueta.modelo
            precio = raqueta.precio
            peso = raqueta.peso
        }

        val res = raquetasRepository.findById(raqueta.id)

        assert(res == raqueta)
    }

    @Test
    fun findByIdNoExiste() {
        val res = raquetasRepository.findById(UUID.randomUUID())

        assert(res == null)

    }

    @Test
    fun saveInsert() {
        val res = raquetasRepository.save(raqueta)

        assert(res == raqueta)
    }

    @Test
    fun saveUpdate() = transaction {
        val insert = RaquetaDao.new(raqueta.id) {
            marca = raqueta.marca
            modelo = raqueta.modelo
            precio = raqueta.precio
            peso = raqueta.peso
        }

        val res = raquetasRepository.save(raqueta)

        assert(res == raqueta)
    }

    @Test
    fun delete() = transaction {
        val insert = RaquetaDao.new(raqueta.id) {
            marca = raqueta.marca
            modelo = raqueta.modelo
            precio = raqueta.precio
            peso = raqueta.peso
        }

        val res = raquetasRepository.delete(raqueta)

        assert(res)
    }

    @Test
    fun deleteNoExiste() {
        val res = raquetasRepository.delete(raqueta)

        assert(!res)
    }
}
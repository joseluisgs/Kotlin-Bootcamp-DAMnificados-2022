package entities

import config.AppConfig
import config.DataBase
import models.Raqueta
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.*
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Para el beforeAll y afterAll
internal class RaquetaDaoTest {

    val raqueta = Raqueta(UUID.randomUUID(), "Marca Test", "Modelo Test", 200.0, 300)

    @BeforeAll
    fun setUp() {
        // Iniciamos el servidor
        DataBase.init(AppConfig.DEFAULT)

    }

    @AfterAll
    fun tearDown() {
        DataBase.dropTables()
    }

    @BeforeEach
    fun init() {
        DataBase.clearTables()
    }

    @Test
    fun getAll() = transaction {
        val raquetaList = RaquetaDao.all()
        assert(raquetaList.empty()) { "No debe haber raquetas" }
    }

    @Test
    fun getById() = transaction {
        val res = RaquetaDao.new(raqueta.id) {
            marca = raqueta.marca
            modelo = raqueta.modelo
            precio = raqueta.precio
            peso = raqueta.peso
        }
        val raquetaById = RaquetaDao.findById(raqueta.id)
        assertAll(
            { assert(raquetaById != null) },
            { assert(raquetaById?.id?.value == raqueta.id) }
        )
    }

    @Test
    fun testInsert() = transaction {
        val res = RaquetaDao.new(raqueta.id) {
            marca = raqueta.marca
            modelo = raqueta.modelo
            precio = raqueta.precio
            peso = raqueta.peso
        }

        assert(res.id.value == raqueta.id)
    }

    @Test
    fun testUpdate() = transaction {
        val res = RaquetaDao.new(raqueta.id) {
            marca = raqueta.marca
            modelo = raqueta.modelo
            precio = raqueta.precio
            peso = raqueta.peso
        }

        res.marca = "Marca Test 2"
        val raquetaById = RaquetaDao.findById(raqueta.id)

        assertAll(
            { assert(res.marca == "Marca Test 2") },
            { assert(raquetaById?.marca == res.marca) },
            { assert(res.id.value == raqueta.id) }
        )
    }

    @Test
    fun testDelete() = transaction {
        val res = RaquetaDao.new(raqueta.id) {
            marca = raqueta.marca
            modelo = raqueta.modelo
            precio = raqueta.precio
            peso = raqueta.peso
        }

        res.delete()
        val raquetaById = RaquetaDao.findById(raqueta.id)

        assert(raquetaById == null)
    }


}
package repositories.raquetas

import ResumenAppModuleDI
import config.AppConfig
import config.DataBase
import entities.RaquetaDao
import models.Raqueta
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import java.util.*

/**
 * Como no estamos mockeando las dependencias estamos haciendo un test unitario que ademas es de integración
 * esto es debido porque propagamos los mensajes entre objetos y comprobamos que dicha cadena de mensajes funciona
 * Pero no tiene que ser necesario hacerlo así.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Para el beforeAll y afterAll
internal class RaquetasRepositoryImplTest : KoinComponent {

    private val raquetasRepository: RaquetasRepository by inject()

    private val raqueta = Raqueta(UUID.randomUUID(), "Marca Test", "Modelo Test", 200.0, 300)

    @BeforeAll
    fun setUp() {
        startKoin {
            modules(ResumenAppModuleDI)
        }

        DataBase.init(AppConfig.DEFAULT)
    }

    @AfterAll
    fun tearDown() {
        DataBase.dropTables()
        stopKoin()
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
        RaquetaDao.new(raqueta.id) {
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
        RaquetaDao.new(raqueta.id) {
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
        RaquetaDao.new(raqueta.id) {
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
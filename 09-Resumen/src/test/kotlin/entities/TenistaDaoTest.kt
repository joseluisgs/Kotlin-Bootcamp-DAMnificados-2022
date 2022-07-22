package entities

import config.AppConfig
import config.DataBase
import models.Tenista
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.*
import java.time.LocalDate

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class TenistaDaoTest {

    val tenista = Tenista(
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
        val raquetaList = TenistaDao.all()
        assert(raquetaList.empty()) { "No debe haber tenistas" }
    }

    @Test
    fun getById() = transaction {
        val res = TenistaDao.new(tenista.id) {
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
        val tenistaById = TenistaDao.findById(tenista.id)

        assertAll(
            { assert(tenistaById != null) },
            { assert(tenistaById?.id?.value == tenista.id) }
        )
    }

    @Test
    fun testInsert() = transaction {
        val res = TenistaDao.new(tenista.id) {
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

        assert(res.id.value == tenista.id)
    }

    @Test
    fun testUpdate() = transaction {
        val res = TenistaDao.new(tenista.id) {
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

        res.nombre = "Tenista Test 2"
        val tenistaById = TenistaDao.findById(tenista.id)

        assertAll(
            { assert(res.nombre == "Tenista Test 2") },
            { assert(tenistaById?.nombre == res.nombre) },
            { assert(res.id.value == tenista.id) }
        )
    }

    @Test
    fun testDelete() = transaction {
        val res = TenistaDao.new(tenista.id) {
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

        res.delete()
        val tenistaById = TenistaDao.findById(tenista.id)

        assert(tenistaById == null)
    }
}
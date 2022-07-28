package repositories.raqueta

import entities.RaquetaDao
import entities.RaquetasTable
import mappers.fromRaquetaDaoToRaqueta
import models.Raqueta
import mu.KotlinLogging
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

private val logger = KotlinLogging.logger {}

class RaquetasRepositoryImpl(
    private val raquetaDao: UUIDEntityClass<RaquetaDao>,
    // private val tenistaDao: UUIDEntityClass<TenistaDao>
) : RaquetasRepository {

    override fun findAll(): List<Raqueta> = transaction {
        logger.debug { "findAll()" }
        raquetaDao.all().map { it.fromRaquetaDaoToRaqueta() }
    }

    override fun findById(id: UUID): Raqueta? = transaction {
        logger.debug { "findById($id)" }
        raquetaDao.findById(id)
            ?.fromRaquetaDaoToRaqueta() //?: throw RaquetaException("Raqueta no encontrada con id: $id") // No es obligatorio el throw, porque devolvemos Raqueta? lo sería si es Raqueta
    }

    fun findByMarca(marca: String): List<Raqueta> = transaction {
        logger.debug { "findByMarca($marca)" }
        raquetaDao.find { RaquetasTable.marca eq marca }.map { it.fromRaquetaDaoToRaqueta() }
    }

    override fun save(entity: Raqueta): Raqueta = transaction {
        // Existe?
        val existe = raquetaDao.findById(entity.id)
        // Esta alrternativa let/run es muy usada en Kotlin, como el if else...

        existe?.let {
            // Si existe actualizamos
            logger.debug { "save($entity) - actualizando" }
            existe.apply {
                marca = entity.marca
                precio = entity.precio
                /*tenistas = SizedCollection(entity.tenistas.map {
                    tenistaDao.findById(it.id) ?: throw TenistaException("Tenista no encontrado con id: ${it.id}")
                })*/
            }.fromRaquetaDaoToRaqueta()
        } ?: run {
            logger.debug { "save($entity) - creando" }
            // No existe, lo guardamos, le pongo el ID que he generado en el DATA val, si no podría haber usado el suyo sin paranetesis
            raquetaDao.new(entity.id) {
                marca = entity.marca
                precio = entity.precio
                /*tenistas = SizedCollection(entity.tenistas.map {
                    tenistaDao.findById(it.id) ?: throw TenistaException("Tenista no encontrado con id: ${it.id}")
                })*/
            }.fromRaquetaDaoToRaqueta()
        }
    }


    override fun delete(entity: Raqueta): Boolean = transaction {
        // Existe?
        val existe = raquetaDao.findById(entity.id) ?: return@transaction false
        // Si existe lo borramos
        logger.debug { "delete($entity) - borrando" }
        existe.delete()
        true
    }

    // Esto me lo he invitado a hacerlo, pero no lo he hecho, porque no lo he hecho en el curso...
    /*override fun getTenistas(entity: Raqueta): List<Tenista> = transaction {
        tenistaDao.all().filter { it.raqueta.id.value == entity.id }.map { it.fromTenistaDaoToTenista() }
    }*/
}


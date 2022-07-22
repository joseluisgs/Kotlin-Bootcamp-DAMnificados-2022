package repositories.raquetas

import entities.RaquetaDao
import mappers.fromRaquetaDaoToRaqueta
import models.Raqueta
import mu.KotlinLogging
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

private val logger = KotlinLogging.logger {}

/**
 * Implementación de la interfaz [RaquetaRepository] usando Exposed.
 */

class RaquetasRepositoryImpl(
    private val raquetaDao: UUIDEntityClass<RaquetaDao>
) : RaquetasRepository {

    /**
     * Obtiene todas las raquetas.
     * @return Lista de raquetas.
     */
    override fun findAll(): List<Raqueta> = transaction {
        logger.debug { "findAll()" }
        raquetaDao.all().map { it.fromRaquetaDaoToRaqueta() }
    }

    /**
     * Obtiene una raqueta por su id.
     * @param id Id de la raqueta.
     * @return Raqueta? si existe, null en caso contrario.
     */
    override fun findById(id: UUID): Raqueta? = transaction {
        logger.debug { "findById($id)" }
        raquetaDao.findById(id)
            ?.fromRaquetaDaoToRaqueta()
    }

    /**
     * Crea una nueva raqueta si no existe, si existe la actualiza.
     * @param entity Raqueta a crear o actualizar.
     * @return Raqueta creada o actualizada.
     */
    override fun save(entity: Raqueta): Raqueta = transaction {
        // Existe?
        val existe = raquetaDao.findById(entity.id)
        // Esta alrternativa let/run es muy usada en Kotlin, como el if else...
        existe?.let {
            // Si existe actualizamos
            logger.debug { "save($entity) - actualizando" }
            existe.apply {
                marca = entity.marca
                modelo = entity.modelo
                precio = entity.precio
                peso = entity.peso
            }.fromRaquetaDaoToRaqueta()
        } ?: run {
            logger.debug { "save($entity) - creando" }
            // No existe, lo guardamos, le pongo el ID que he generado en el DATA val, si no podría haber usado el suyo sin paranetesis
            raquetaDao.new(entity.id) {
                marca = entity.marca
                modelo = entity.modelo
                precio = entity.precio
                peso = entity.peso
            }.fromRaquetaDaoToRaqueta()
        }
    }

    /**
     * Elimina una raqueta
     * @param entity Raqueta a eliminar.
     * @return Boolean indicando si se eliminó o no.
     */
    override fun delete(entity: Raqueta): Boolean = transaction {
        // Existe?
        val existe = raquetaDao.findById(entity.id) ?: return@transaction false
        // Si existe lo borramos
        logger.debug { "delete($entity) - borrando" }
        existe.delete()
        true
    }
}


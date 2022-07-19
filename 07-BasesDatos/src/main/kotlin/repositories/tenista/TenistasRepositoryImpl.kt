package repositories.tenista

import Exceptions.RaquetaException
import entities.RaquetaDao
import entities.TenistaDao
import mappers.fromTenistaDaoToTenista
import model.Tenista
import mu.KotlinLogging
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

private val logger = KotlinLogging.logger {}

class TenistasRepositoryImpl(
    private val tenistaDao: UUIDEntityClass<TenistaDao>,
    private val raquetaDao: UUIDEntityClass<RaquetaDao>
) : TenistasRepository {
    override fun findAll(): List<Tenista> = transaction {
        logger.debug { "findAll()" }
        tenistaDao.all().map { it.fromTenistaDaoToTenista() }
    }

    override fun findById(id: UUID): Tenista? = transaction {
        logger.debug { "findById($id)" }
        tenistaDao.findById(id)?.fromTenistaDaoToTenista()
    }

    override fun save(entity: Tenista): Tenista = transaction {
        // Existe?
        val existe = tenistaDao.findById(entity.id)
        // Esta alrternativa let/run es muy usada en Kotlin, como el if else...
        existe?.let {
            logger.debug { "save($entity) - actualizando" }
            // Si existe actualizamos
            existe.apply {
                nombre = entity.nombre
                ranking = entity.ranking
                fechaNacimiento = entity.fechaNacimiento
                añoProfesional = entity.añoProfesional
                altura = entity.altura
                peso = entity.peso
                ganancias = entity.ganancias
                manoDominante = entity.manoDominante.mano
                tipoReves = entity.tipoReves.tipo
                puntos = entity.puntos
                raqueta = raquetaDao.findById(entity.raqueta!!.id)
                    ?: throw RaquetaException("La raqueta no existe con id: $id")
            }.fromTenistaDaoToTenista()
        } ?: run {
            logger.debug { "save($entity) - creando" }
            // No existe, lo guardamos, le pongo el ID que he generado en el DATA val, si no podría haber usado el suyo sin paranetesis
            tenistaDao.new(entity.id) {
                nombre = entity.nombre
                ranking = entity.ranking
                fechaNacimiento = entity.fechaNacimiento
                añoProfesional = entity.añoProfesional
                altura = entity.altura
                peso = entity.peso
                ganancias = entity.ganancias
                manoDominante = entity.manoDominante.mano
                tipoReves = entity.tipoReves.tipo
                puntos = entity.puntos
                raqueta = raquetaDao.findById(entity.raqueta!!.id)
                    ?: throw RaquetaException("La raqueta no existe con id: $id")
            }.fromTenistaDaoToTenista()
        }
    }

    override fun delete(entity: Tenista): Boolean = transaction {
        val existe = tenistaDao.findById(entity.id) ?: return@transaction false
        // Si existe lo borramos
        logger.debug { "delete($entity) - borrando" }
        existe.delete()
        true
    }
}


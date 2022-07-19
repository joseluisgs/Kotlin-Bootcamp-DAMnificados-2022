package repositories.tenista

import Exceptions.RaquetaException
import entities.RaquetaDao
import entities.TenistaDao
import mappers.fromTenistaDaoToTenista
import model.Tenista
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class TenistaRepositoryImpl(
    private val tenistaDao: UUIDEntityClass<TenistaDao>,
    private val raquetaDao: UUIDEntityClass<RaquetaDao>
) : TenistaRepository {
    override fun findAll(): List<Tenista> = transaction {
        tenistaDao.all().map { it.fromTenistaDaoToTenista() }
    }

    override fun findById(id: UUID): Tenista? = transaction {
        tenistaDao.findById(id)?.fromTenistaDaoToTenista()
    }

    override fun save(entity: Tenista): Tenista = transaction {
        // Existe?
        val existe = tenistaDao.findById(entity.id)
        // Esta alrternativa let/run es muy usada en Kotlin, como el if else...
        existe?.let {
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
                raqueta = raquetaDao.findById(entity.id) ?: throw RaquetaException("La raqueta no existe con id: $id")
            }.fromTenistaDaoToTenista()
        } ?: run {
            // No existe, lo guardamos
            tenistaDao.new {
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
                raqueta = raquetaDao.findById(entity.id) ?: throw RaquetaException("La raqueta no existe con id: $id")
            }.fromTenistaDaoToTenista()
        }
    }

    override fun delete(entity: Tenista): Boolean = transaction {
        val existe = tenistaDao.findById(entity.id) ?: return@transaction false
        // Si existe lo borramos
        existe.delete()
        true
    }
}


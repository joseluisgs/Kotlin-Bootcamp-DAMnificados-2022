package repositories.raqueta

import entities.RaquetaDao
import mappers.fromRaquetaDaoToRaqueta
import model.Raqueta
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class RaquetaRepositoryImpl(
    private val raquetaDao: UUIDEntityClass<RaquetaDao>,
    //private val tenistaDao: UUIDEntityClass<TenistaDao>
) : RaquetaRepository {

    override fun findAll(): List<Raqueta> = transaction {
        raquetaDao.all().map { it.fromRaquetaDaoToRaqueta() }
    }

    override fun findById(id: UUID): Raqueta? = transaction {
        raquetaDao.findById(id)
            ?.fromRaquetaDaoToRaqueta() //?: throw RaquetaException("Raqueta no encontrada con id: $id") // No es obligatorio el throw, porque devolvemos Raqueta? lo sería si es Raqueta
    }

    override fun save(entity: Raqueta): Raqueta = transaction {
        // Existe?
        val existe = raquetaDao.findById(entity.id)
        // Esta alrternativa let/run es muy usada en Kotlin, como el if else...
        existe?.let {
            // Si existe actualizamos
            existe.apply {
                marca = entity.marca
                precio = entity.precio
                /*tenistas = SizedCollection(entity.tenistas.map {
                    tenistaDao.findById(it.id) ?: throw TenistaException("Tenista no encontrado con id: ${it.id}")
                })*/
            }.fromRaquetaDaoToRaqueta()
        } ?: run {
            // No existe, lo guardamos
            raquetaDao.new {
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
        existe.delete()
        true
    }
}


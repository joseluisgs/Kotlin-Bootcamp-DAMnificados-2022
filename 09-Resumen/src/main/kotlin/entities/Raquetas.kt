package entities

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*

/**
 * Tabla de la base de datos que almacena Raquetas
 */
object RaquetasTable : UUIDTable() {
    val marca = varchar("marca", 100)
    val modelo = varchar("modelo", 100)
    val precio = double("precio")
    val peso = integer("peso")
}

/**
 * DAO de la entidad Raquetas
 */

class RaquetaDao(id: EntityID<UUID>) : UUIDEntity(id) {
    // mi id será el de la tabla...
    companion object : UUIDEntityClass<RaquetaDao>(RaquetasTable)

    var marca by RaquetasTable.marca
    var modelo by RaquetasTable.modelo
    var precio by RaquetasTable.precio
    var peso by RaquetasTable.peso

    // Relación inversa donde soy referenciado (tenistas) referrersOn, solo si quiero acceder a ellos
    val tenistas by TenistaDao referrersOn TenistasTable.raqueta  // Si le pongo val no dejo asignar tenistas desde aquí
}
package entities

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.javatime.date
import java.util.*

// Tabla que representa un Torneo de Tenis, decimos que es una tabla con un campo UUID
// https://www.baeldung.com/kotlin/exposed-persistence
// Indicamos el nombre y tipo del campo en SQL
object TorneosTable : UUIDTable() {
    val nombre = varchar("nombre", 100)
    val premio = double("premio")
    val fecha = date("fecha")
}


// DAO de Torneo
class TorneoDao(id: EntityID<UUID>) : UUIDEntity(id) {
    // Mi id sobre que id está enlazada de la tabla
    companion object : UUIDEntityClass<TorneoDao>(TorneosTable)

    var nombre by TorneosTable.nombre
    var premio by TorneosTable.premio
    var fecha by TorneosTable.fecha

    // Ponemos una lista de tenistas que pertenecen a este torneo
    var tenistas by TenistaDao via TorneosTenistasTable
}
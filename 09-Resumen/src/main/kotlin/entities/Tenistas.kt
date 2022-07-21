package entities

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.javatime.date
import java.util.*

// Tabla de Tenistas
object TenistasTable : UUIDTable() {
    val nombre = varchar("nombre", 100)
    val ranking = integer("ranking")
    val fechaNacimiento = date("fecha_nacimiento")
    val añoProfesional = integer("año_profesional")
    val altura = integer("altura")
    val peso = integer("peso")
    val ganancias = double("ganancias")
    val manoDominante = varchar("mano_dominante", 20)
    val tipoReves = varchar("tipo_reves", 20)
    val puntos = integer("puntos")

    // Mi raqueta, un tenista lleva un modelo de raqueta, es referenciada por el id de la raqueta clave de raqueta se propaga en la tabla tenistas
    val raqueta = reference("raqueta_id", RaquetasTable)
}


// DAO de Tenista Clase que mapea la fila de la tabla Tenistas con id uuid
class TenistaDao(id: EntityID<UUID>) : UUIDEntity(id) {

    // mi id será el de la tabla...
    companion object : UUIDEntityClass<TenistaDao>(TenistasTable)

    // mis propiedades con qué columnas de la tabla corresponden
    var nombre by TenistasTable.nombre
    var ranking by TenistasTable.ranking
    var fechaNacimiento by TenistasTable.fechaNacimiento
    var añoProfesional by TenistasTable.añoProfesional
    var altura by TenistasTable.altura
    var peso by TenistasTable.peso
    var ganancias by TenistasTable.ganancias
    var manoDominante by TenistasTable.manoDominante
    var tipoReves by TenistasTable.tipoReves
    var puntos by TenistasTable.puntos

    // Clave externa a raqueta -- Debemos tener en cuenta los probelmas de la direccionalidad...
    var raqueta by RaquetaDao referencedOn TenistasTable.raqueta  // Si le pongo var dejo asignar la raqueta a la tabla tenista.
    
}
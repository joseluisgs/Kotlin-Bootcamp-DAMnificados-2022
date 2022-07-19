package entities

import org.jetbrains.exposed.sql.Table

// Es una Muchos a muchos
// un tenista juega muchos torneos y un torneo tiene muchos tenistas
object TorneosTenistasTable : Table("TORNEOS_TENISTAS") {
    val torneo = reference("torneo_id", TorneosTable)
    val tenista = reference("tenista_id", TenistasTable)

    override val primaryKey = PrimaryKey(
        torneo, tenista,
        name = "PK_Torneos_Tenistas"  // Esto es opcional
    )
}

// DAO de TorneosTenistas
/*
class TorneoTenistaDao(id: EntityID) : UUIDEntity(id) {
    companion object : UUIDEntityClass<TorneoTenistaDao>(TorneosTenistasTable)

    var torneo by TorneoDao referencedOn TorneosTenistasTable.torneo
    var tenista by TenistaDao referencedOn TorneosTenistasTable.tenista
}*/

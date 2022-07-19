package model

import java.util.*

data class Raqueta(
    val id: UUID = UUID.randomUUID(),
    var marca: String,
    var precio: Double
    // val tenistas: List<Tenista>? = null
)

// No voy a guardar para las raquetas los tenistas, porque eso lo puedo consultar de otras maneras ...
// Si no tendríamos un problema de recursividad... Ahora no lo ves, pero seguro que en Acceso a Datos lo ves!!
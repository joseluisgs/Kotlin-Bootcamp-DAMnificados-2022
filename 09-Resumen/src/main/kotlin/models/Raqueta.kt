package models

import java.util.*

/**
 * Modelo de datos para Raqueta
 */
data class Raqueta(
    val id: UUID = UUID.randomUUID(),
    var marca: String,
    val modelo: String,
    var precio: Double,
    var peso: Int,
)

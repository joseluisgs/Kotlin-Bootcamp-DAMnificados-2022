package models

import utils.toLocalMoney
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
) {
    override fun toString(): String {
        return "Raqueta(id=$id, marca='$marca', modelo='$modelo', precio=${precio.toLocalMoney()}, peso=$peso)"
    }
}

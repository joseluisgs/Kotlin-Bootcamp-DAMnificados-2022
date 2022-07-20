package dto

import kotlinx.serialization.Serializable

/**
 * DTO de Raqueta para CSV y JSON
 */
@Serializable
class RaquetaDto(
    val id: String,
    val marca: String,
    val modelo: String,
    val precio: Double,
    val peso: Int
)

package dto

import kotlinx.serialization.Serializable

@Serializable
class RaquetaDto(
    val id: String,
    val marca: String,
    val modelo: String,
    val precio: Double,
    val peso: Int
)

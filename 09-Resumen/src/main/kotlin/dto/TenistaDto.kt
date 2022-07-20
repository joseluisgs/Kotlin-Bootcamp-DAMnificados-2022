package dto

import kotlinx.serialization.Serializable


@Serializable
data class TenistaDto(
    val id: String,
    val nombre: String,
    val ranking: Int,
    // Si queremos cambiar el formato del campo en JSON, aquí dos ejemplos
    // @SerialName("fechaNacimiento")
    val fechaNacimiento: String,
    val añoProfesional: Int,
    val altura: Int,
    val peso: Int,
    val ganancias: Double,
    val manoDominante: String,
    val tipoReves: String,
    val puntos: Int,
    val raqueta: RaquetaDto? = null
)


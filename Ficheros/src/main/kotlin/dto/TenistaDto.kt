package dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// El DTO nos servira de trasnferencia y podemos usar los datos que queramos o combinar de distintas formas.
@Serializable // Para que se pueda serializar entre otras a cosas a JSON
data class TenistaDto(
    val uuid: String,
    val nombre: String,
    val ranking: Int,
    // Si queremos cambiar el formato del campo en JSON, aquí dos ejemplos
    @SerialName("tipo_reves")
    val fechaNacimiento: String,
    @SerialName("anoProfesional")
    val añoProfesional: Int,
    val altura: Int,
    val peso: Int,
    val ganancias: Double,
    val manoDominante: String,
    val tipoReves: String,
    val puntos: Int
)


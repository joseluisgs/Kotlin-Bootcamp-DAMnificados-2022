package dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import model.Tenista
import java.time.LocalDate
import java.util.*

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
) {
    fun toTenista(): Tenista {
        return Tenista(
            uuid = UUID.fromString(uuid),
            nombre = nombre,
            ranking = ranking,
            fechaNacimiento = LocalDate.parse(fechaNacimiento),
            añoProfesional = añoProfesional,
            altura = altura,
            peso = peso,
            ganancias = ganancias,
            manoDominante = Tenista.ManoDominante.from(manoDominante),
            tipoReves = Tenista.TipoReves.from(tipoReves),
            puntos = puntos
        )
    }

    fun toCsvLine(): String {
        return "$uuid,$nombre,$ranking,$fechaNacimiento,$añoProfesional,$altura,$peso,$ganancias,$manoDominante,$tipoReves,$puntos"
    }

    companion object {
        fun fromCsvLine(line: String): TenistaDto {
            val campos = line.split(",")
            return TenistaDto(
                uuid = campos[0],
                nombre = campos[1],
                ranking = campos[2].toInt(),
                fechaNacimiento = campos[3],
                añoProfesional = campos[4].toInt(),
                altura = campos[5].toInt(),
                peso = campos[6].toInt(),
                ganancias = campos[7].toDouble(),
                manoDominante = campos[8],
                tipoReves = campos[9],
                puntos = campos[10].toInt(),
            )
        }

        fun fromTenista(tenista: Tenista): TenistaDto {
            return TenistaDto(
                uuid = tenista.uuid.toString(),
                nombre = tenista.nombre,
                ranking = tenista.ranking,
                fechaNacimiento = tenista.fechaNacimiento.toString(),
                añoProfesional = tenista.añoProfesional,
                altura = tenista.altura,
                peso = tenista.peso,
                ganancias = tenista.ganancias,
                manoDominante = tenista.manoDominante.mano,
                tipoReves = tenista.tipoReves.tipo,
                puntos = tenista.puntos
            )
        }
    }
}


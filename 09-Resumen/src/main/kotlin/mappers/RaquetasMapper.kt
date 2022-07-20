package mappers

import dto.RaquetaDto
import models.Raqueta
import java.util.*


/**
 * Extensiones de RaquetaDto a Raqueta
 */
fun RaquetaDto.fromRaquetaDtoToRaqueta(): Raqueta {
    return Raqueta(UUID.fromString(id), marca, modelo, precio, peso)
}

/**
 * Extension de String CSV a RaquetaDto
 */
fun String.fromCsvLineToRaquetaDto(): RaquetaDto {
    val (id, marca, modelo, precio, peso) = this.split(',').map { it.trim() }
    return RaquetaDto(id, marca, modelo, precio.toDouble(), peso.toInt())

}

/**
 * Extension de Raqueta a RaquetaDto
 */
fun Raqueta.fromRaquetaToRaquetaDto(): RaquetaDto {
    return RaquetaDto(id.toString(), marca, modelo, precio, peso)
}

/**
 * Extension de RaquetaDto a String CSV Linea
 */
fun RaquetaDto.fromRaquetaDtoToCsvLine(): String {
    return "$id,$marca,$modelo,$precio,$peso"
}



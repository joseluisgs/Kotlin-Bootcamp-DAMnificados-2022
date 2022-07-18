package mappers

import dto.TenistaDto
import model.Tenista
import java.time.LocalDate
import java.util.*

// Mapeadores de dados para Tenista

fun String.fromCsvLineToTenistaDto(): TenistaDto {
    val campos = this.split(",").map { it.trim() } // Si hay espacios en blanco, los quita
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

fun TenistaDto.fromTenistaDtoToCsvLine(): String {
    return "$uuid,$nombre,$ranking,$fechaNacimiento,$añoProfesional,$altura,$peso,$ganancias,$manoDominante,$tipoReves,$puntos"
}


fun TenistaDto.fromTenistaDtoToTenista(): Tenista {
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

fun Tenista.fromTenistaToTenistaDto(): TenistaDto {
    return TenistaDto(
        uuid = uuid.toString(),
        nombre = nombre,
        ranking = ranking,
        fechaNacimiento = fechaNacimiento.toString(),
        añoProfesional = añoProfesional,
        altura = altura,
        peso = peso,
        ganancias = ganancias,
        manoDominante = manoDominante.mano,
        tipoReves = tipoReves.tipo,
        puntos = puntos
    )
}



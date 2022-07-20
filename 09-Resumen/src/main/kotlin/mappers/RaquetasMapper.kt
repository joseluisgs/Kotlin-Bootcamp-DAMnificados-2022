package mappers

import dto.RaquetaDto
import models.Raqueta
import java.util.*

fun RaquetaDto.fromRaquetaDtoToRaqueta(): Raqueta {
    return Raqueta(UUID.fromString(id), marca, modelo, precio, peso)
}
package mappers

import entities.RaquetaDao
import models.Raqueta

fun RaquetaDao.fromRaquetaDaoToRaqueta(): Raqueta {
    return Raqueta(
        id = id.value,
        marca = marca,
        precio = precio,
        //tenistas = tenistas.map { it.fromTenistaDaoToTenista() }
    )
}





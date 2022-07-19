package mappers

import entities.TenistaDao
import model.Tenista

// Mapeadores de dados para Tenista

fun TenistaDao.fromTenistaDaoToTenista(): Tenista {
    return Tenista(
        id = id.value,
        nombre = nombre,
        ranking = ranking,
        fechaNacimiento = fechaNacimiento,
        añoProfesional = añoProfesional,
        altura = altura,
        peso = peso,
        ganancias = ganancias,
        manoDominante = Tenista.ManoDominante.from(manoDominante),
        tipoReves = Tenista.TipoReves.from(tipoReves),
        puntos = puntos,
        raqueta = raqueta.fromRaquetaDaoToRaqueta(),
    )
}


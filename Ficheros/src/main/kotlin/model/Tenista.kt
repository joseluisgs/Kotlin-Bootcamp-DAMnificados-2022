package model

import extensions.toLocalDate
import extensions.toLocalMoney
import extensions.toLocalNumber
import java.time.LocalDate
import java.util.*

data class Tenista(
    val uuid: UUID = UUID.randomUUID(),
    val nombre: String,
    val ranking: Int,
    val fechaNacimiento: LocalDate,
    val añoProfesional: Int,
    val altura: Int,
    val peso: Int,
    val ganancias: Double,
    val manoDominante: ManoDominante,
    val tipoReves: TipoReves,
    val puntos: Int
) {

    override fun toString(): String {
        return "Tenista(uuid=$uuid, nombre='$nombre', ranking=$ranking, " +
                "fechaNacimiento=${fechaNacimiento.toLocalDate()}, " +
                "añoProfesional=$añoProfesional, " +
                "altura=${(altura.toDouble() / 100).toLocalNumber()} cm, " +
                "peso=$peso Kg, " +
                "ganancias=${ganancias.toLocalMoney()}, " +
                "manoDominante=${manoDominante.mano}, " +
                "tipoReves=${tipoReves.tipo}, " +
                "puntos=$puntos)"
    }

    // ENUMS de la propia clase
    enum class ManoDominante(val mano: String) {
        DERECHA("DERECHA"),
        IZQUIERDA("IZQUIERDA");

        companion object {
            fun from(manoDominante: String): ManoDominante {
                return when (manoDominante) {
                    "DERECHA" -> DERECHA
                    "IZQUIERDA" -> IZQUIERDA
                    else -> throw IllegalArgumentException("ManoDominante no reconocida")
                }
            }
        }
    }

    enum class TipoReves(val tipo: String) {
        UNA_MANO("UNA MANO"),
        DOS_MANOS("DOS MANOS");

        companion object {
            fun from(tipoReves: String): TipoReves {
                return when (tipoReves) {
                    "UNA MANO" -> UNA_MANO
                    "DOS MANOS" -> DOS_MANOS
                    else -> throw IllegalArgumentException("TipoReves no reconocida")
                }
            }
        }
    }


}
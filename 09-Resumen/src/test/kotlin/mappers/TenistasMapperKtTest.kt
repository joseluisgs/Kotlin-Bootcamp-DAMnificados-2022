package mappers

import dto.TenistaDto
import models.Tenista
import org.junit.jupiter.api.Test
import java.time.LocalDate

internal class TenistasMapperKtTest {

    private val tenista = Tenista(
        nombre = "Rafael Nadal",
        ranking = 3,
        fechaNacimiento = LocalDate.parse("1985-06-04"),
        añoProfesional = 2005,
        altura = 185,
        peso = 80,
        ganancias = 10000000.0,
        manoDominante = Tenista.ManoDominante.from("Izquierda"),
        tipoReves = Tenista.TipoReves.from("dos manos"),
        puntos = 6789,
    )

    private val tenistaDto = TenistaDto(
        id = tenista.id.toString(),
        nombre = tenista.nombre,
        ranking = tenista.ranking,
        fechaNacimiento = tenista.fechaNacimiento.toString(),
        añoProfesional = tenista.añoProfesional,
        altura = tenista.altura,
        peso = tenista.peso,
        ganancias = tenista.ganancias,
        manoDominante = tenista.manoDominante.mano,
        tipoReves = tenista.tipoReves.tipo,
        puntos = tenista.puntos,
    )


    private val line =
        "${tenistaDto.id},${tenistaDto.nombre},${tenistaDto.ranking},${tenistaDto.fechaNacimiento},${tenistaDto.añoProfesional},${tenistaDto.altura},${tenistaDto.peso},${tenistaDto.ganancias},${tenistaDto.manoDominante},${tenistaDto.tipoReves},${tenistaDto.puntos}"

    @Test
    fun fromCsvLineToTenistaDto() {
        assert(tenistaDto == line.fromCsvLineToTenistaDto())
    }

    @Test
    fun fromTenistaDtoToTenista() {
        assert(tenista == tenistaDto.fromTenistaDtoToTenista())
    }

    @Test
    fun fromTenistaToTenistaDto() {
        assert(tenistaDto == tenista.fromTenistaToTenistaDto())
    }

    @Test
    fun fromTenistaDtoToCsvLine() {
        assert(line == tenistaDto.fromTenistaDtoToCsvLine())
    }

}
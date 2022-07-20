package mappers

import dto.RaquetaDto
import models.Raqueta
import org.junit.jupiter.api.Test
import java.util.*

internal class RaquetasMapperKtTest {

    private val raqueta = Raqueta(UUID.randomUUID(), "Marca Test", "Modelo Test", 200.0, 300)
    private val raquetaDto =
        RaquetaDto(raqueta.id.toString(), raqueta.marca, raqueta.modelo, raqueta.precio, raqueta.peso)
    private val line = "${raqueta.id},${raqueta.marca},${raqueta.modelo},${raqueta.precio},${raqueta.peso}"

    @Test
    fun romRaquetaDtoToRaqueta() {
        assert(raqueta == raquetaDto.fromRaquetaDtoToRaqueta())
    }

    @Test
    fun fromCsvLineToRaquetaDto() {
        assert(raquetaDto == line.fromCsvLineToRaquetaDto())
    }

    @Test
    fun fromRaquetaToRaquetaDto() {
        assert(raquetaDto == raqueta.fromRaquetaToRaquetaDto())
    }

    @Test
    fun fromRaquetaDtoToCsvLine() {
        assert(line == raquetaDto.fromRaquetaDtoToCsvLine())
    }
}
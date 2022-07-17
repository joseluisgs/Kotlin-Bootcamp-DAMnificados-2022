package repositories

import dto.TenistaDto
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import model.Tenista
import mu.KotlinLogging
import java.io.File

// Mi logger
private val logger = KotlinLogging.logger {}

class TenistasRepository {
    private var tenistas = listOf<Tenista>()

    fun getTenistas(): List<Tenista> {
        return tenistas
    }

    fun loadFromCsv(csvFile: String) {
        // Comprobamos que exiete el fichero
        if (!File(csvFile).exists()) {
            logger.error("No existe el fichero $csvFile")
            throw IllegalArgumentException("El fichero $csvFile no existe")
        }
        // Vamos a leer linea a linea el fichero
        logger.debug("Leyendo fichero csv...")
        tenistas = File(csvFile).readLines().drop(1).map {
            TenistaDto.fromCsvLine(it).toTenista()
        }
        logger.debug("Tenistas cargados desde csv: ${tenistas.size}")
    }

    fun saveToJson(jsonFile: String) {
        val tenistasDtos = tenistas.map { TenistaDto.fromTenista(it) }
        val format = Json { prettyPrint = true }
        val json = format.encodeToString(tenistasDtos)
        logger.debug("salvando a json...")
        File(jsonFile).writeText(json)
        logger.debug("Tenistas guardados en json: ${tenistas.size}")
    }

    fun loadFromJson(jsonFile: String) {
        // Comprobamos que existe el fichero
        if (!File(jsonFile).exists()) {
            logger.error("No existe el fichero $jsonFile")
            throw IllegalArgumentException("El fichero $jsonFile no existe")
        }
        logger.debug("Leyendo fichero json...")
        val json = File(jsonFile).readText()
        val tenistasDtos = Json.decodeFromString<List<TenistaDto>>(json)
        tenistas = tenistasDtos.map { it.toTenista() }
        logger.debug("Tenistas cargados desde json: ${tenistas.size}")
    }

    fun saveToCsv(csvFile: String) {
        val header =
            "uuid,nombre,ranking,fecha_nacimiento,año_profesional,altura,peso,ganancias,mano_dominante,tipo_reves,puntos"

        logger.debug("salvando a csv...")
        File(csvFile).writeText(header + "\n")
        val csv = tenistas.map { TenistaDto.fromTenista(it) }.joinToString(separator = "\n") { it.toCsvLine() }
        File(csvFile).appendText(csv + "\n")
        logger.debug("Tenistas guardados en csv: ${tenistas.size}")
    }
}





package services.tenistas

import mappers.fromCsvLineToTenistaDto
import mappers.fromTenistaDtoToCsvLine
import mappers.fromTenistaDtoToTenista
import mappers.fromTenistaToTenistaDto
import models.Tenista
import mu.KotlinLogging
import java.io.File

/**
 * Implementación de la interfaz StorageTenistasCsvService
 */

private val logger = KotlinLogging.logger {}

class StorageTenistasCsvServiceImpl : StorageTenistasCsvService {
    /**
     *  Método que devuelve una lista de tenistas de un archivo csv
     *  @param fileName archivo csv
     *  @return lista de tenistas
     */
    override fun loadFromFile(fileName: String): List<Tenista> {
        if (!File(fileName).exists()) {
            logger.error { "No existe el fichero $fileName" }
            throw IllegalArgumentException("El fichero $fileName no existe")
        }
        // Vamos a leer linea a linea el fichero y la procesamos de DTO a modelo
        logger.debug { "Leyendo fichero csv $fileName..." }
        val tenistas = File(fileName).readLines().drop(1)
            .map { it.fromCsvLineToTenistaDto().fromTenistaDtoToTenista() }
        logger.debug("Tenistas cargados desde csv: ${tenistas.size}")
        return tenistas
    }

    override fun saveToFile(fileName: String, data: List<Tenista>) {
        val header =
            "id,nombre,ranking,fecha_nacimiento,año_profesional,altura,peso,ganancias,mano_dominante,tipo_reves,puntos"
        logger.debug { "Guardando tenistas en fichero csv $fileName..." }
        File(fileName).writeText(header + "\n")
        val tenistas = data.map { it.fromTenistaToTenistaDto().fromTenistaDtoToCsvLine() }
        File(fileName).writeText(tenistas.joinToString("\n"))
        logger.debug("Tenistas guardados en csv: ${tenistas.size}")
    }
}



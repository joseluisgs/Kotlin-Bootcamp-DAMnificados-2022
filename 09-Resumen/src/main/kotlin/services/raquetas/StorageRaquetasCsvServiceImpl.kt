package services.raquetas

import mappers.fromCsvLineToRaquetaDto
import mappers.fromRaquetaDtoToCsvLine
import mappers.fromRaquetaDtoToRaqueta
import mappers.fromRaquetaToRaquetaDto
import models.Raqueta
import mu.KotlinLogging
import java.io.File

/**
 * Implementación de la interfaz de servicio de raquetas.
 */

private val logger = KotlinLogging.logger {}

class StorageRaquetasCsvServiceImpl : StorageRaquetasCsvService {
    /**
     * Método que devuelve una lista de raquetas de un archivo csv.
     * @param fileName Archivo csv.
     * @return Lista de raquetas.
     */
    override fun loadFromFile(fileName: String): List<Raqueta> {
        if (!File(fileName).exists()) {
            logger.error { "No existe el fichero $fileName" }
            throw IllegalArgumentException("El fichero $fileName no existe")
        }
        // Vamos a leer linea a linea el fichero y la procesamos de DTO a modelo
        logger.debug { "Leyendo fichero csv $fileName..." }
        val raquetas = File(fileName).readLines().drop(1)
            .map { it.fromCsvLineToRaquetaDto().fromRaquetaDtoToRaqueta() }
        logger.debug("Raquetas cargadas desde csv: ${raquetas.size}")
        return raquetas
    }

    override fun saveToFile(fileName: String, data: List<Raqueta>) {
        val header = "id,marca,modelo, precio, peso"
        logger.debug { "Guardando raquetas en fichero csv $fileName..." }
        File(fileName).writeText(header + "\n")
        val raquetas = data.map { it.fromRaquetaToRaquetaDto().fromRaquetaDtoToCsvLine() }
        File(fileName).appendText(raquetas.joinToString("\n"))
        logger.debug("Raquetas guardadas en fichero csv: ${raquetas.size}")
    }
}


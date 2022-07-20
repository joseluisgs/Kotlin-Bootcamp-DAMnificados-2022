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
     * @param file Archivo csv.
     * @return Lista de raquetas.
     */
    override fun loadFromFile(file: File): List<Raqueta> {
        if (!file.exists()) {
            logger.error { "No existe el fichero $file" }
            throw IllegalArgumentException("El fichero $file no existe")
        }
        // Vamos a leer linea a linea el fichero y la procesamos de DTO a modelo
        logger.debug { "Leyendo fichero csv $file..." }
        val raquetas = file.readLines().drop(1)
            .map { it.fromCsvLineToRaquetaDto().fromRaquetaDtoToRaqueta() }
        logger.debug("Raquetas cargadas desde csv: ${raquetas.size}")
        return raquetas
    }

    /**
     * Método que guarda una lista de raquetas en un archivo csv.
     * @param file Archivo csv.
     * @param data Lista de raquetas.
     */
    override fun saveToFile(file: File, data: List<Raqueta>) {
        val header = "id,marca,modelo, precio, peso"
        logger.debug { "Guardando raquetas en fichero csv $file..." }
        file.writeText(header + "\n")
        val raquetas = data.map { it.fromRaquetaToRaquetaDto().fromRaquetaDtoToCsvLine() }
        file.appendText(raquetas.joinToString("\n"))
        logger.debug("Raquetas guardadas en fichero csv: ${raquetas.size}")
    }
}


package services.tenistas

import mappers.fromCsvLineToTenistaDto
import mappers.fromTenistaDtoToTenista
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
        logger.debug { "Leyendo fichero csv..." }
        val tenistas = File(fileName).readLines().drop(1)
            .map { it.fromCsvLineToTenistaDto().fromTenistaDtoToTenista() }
        logger.debug("Tenistas cargados desde csv: ${tenistas.size}")
        return tenistas
    }

    override fun saveToFile(fileName: String, data: List<Tenista>) {
        TODO("Not yet implemented")
    }
}



package services.raquetas

import dto.RaquetaDto
import mappers.fromRaquetaDtoToRaqueta
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
     */
    override fun loadFromFile(fileName: String): List<Raqueta> {
        if (!File(fileName).exists()) {
            logger.error { "No existe el fichero $fileName" }
            throw IllegalArgumentException("El fichero $fileName no existe")
        }
        // Vamos a leer linea a linea el fichero y la procesamos de DTO a modelo
        logger.debug { "Leyendo fichero csv..." }
        val raquetas = File(fileName).readLines().drop(1).map { line ->
            val (id, marca, modelo, precio, peso) = line.split(",")
            RaquetaDto(id, marca, modelo, precio.toDouble(), peso.toInt())
        }.map { it.fromRaquetaDtoToRaqueta() }
        logger.debug("Tenistas cargados desde csv: ${raquetas.size}")
        return raquetas
    }

    override fun saveToFile(fileName: String, data: List<Raqueta>) {
        TODO("Not yet implemented")
    }
}


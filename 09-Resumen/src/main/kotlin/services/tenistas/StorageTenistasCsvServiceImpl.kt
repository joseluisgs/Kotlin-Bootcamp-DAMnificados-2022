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
     *  @param file archivo csv
     *  @return lista de tenistas
     */
    override fun loadFromFile(file: File): List<Tenista> {
        if (!file.exists()) {
            logger.error { "No existe el fichero $file" }
            throw IllegalArgumentException("El fichero $file no existe")
        }
        // Vamos a leer linea a linea el fichero y la procesamos de DTO a modelo
        logger.debug { "Leyendo fichero csv $file..." }
        val tenistas = file.readLines().drop(1)
            .map { it.fromCsvLineToTenistaDto().fromTenistaDtoToTenista() }
        logger.debug("Tenistas cargados desde csv: ${tenistas.size}")
        return tenistas
    }

    /**
     * Método que guarda una lista de tenistas en un archivo csv
     * @param file archivo csv
     * @param data lista de tenistas
     */
    override fun saveToFile(file: File, data: List<Tenista>) {
        val header =
            "id,nombre,ranking,fecha_nacimiento,año_profesional,altura,peso,ganancias,mano_dominante,tipo_reves,puntos"
        logger.debug { "Guardando tenistas en fichero csv $file..." }
        file.writeText(header + "\n")
        val tenistas = data.map { it.fromTenistaToTenistaDto().fromTenistaDtoToCsvLine() }
        file.appendText(tenistas.joinToString("\n"))
        logger.debug("Tenistas guardados en csv: ${tenistas.size}")
    }
}



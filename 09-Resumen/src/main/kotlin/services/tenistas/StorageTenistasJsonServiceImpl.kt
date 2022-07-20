package services.tenistas

import dto.TenistaDto
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import mappers.fromTenistaDtoToTenista
import mappers.fromTenistaToTenistaDto
import models.Tenista
import mu.KotlinLogging
import java.io.File

/**
 * Implementación de la interfaz StoreTenistaJsonService
 */

private val logger = KotlinLogging.logger {}

class StorageTenistasJsonServiceImpl : StorageTenistasJsonService {

    /**
     * Recupera una lista de tenistas de un archivo JSON
     * @param file Archivo JSON
     * @return Lista de tenistas
     */
    override fun loadFromFile(file: File): List<Tenista> {
        if (!file.exists()) {
            logger.error("No existe el fichero $file")
            throw IllegalArgumentException("El fichero $file no existe")
        }
        logger.debug { "Cargando tenistas de $file" }
        val json = file.readText()
        val tenistasDto = Json.decodeFromString<List<TenistaDto>>(json)
        val tenistas = tenistasDto.map { it.fromTenistaDtoToTenista() }
        logger.debug { "Tenistas cargados desde json: ${tenistas.size}" }
        return tenistas
    }

    /**
     * Guarda una lista de tenistas en un archivo JSON
     * @param data Lista de tenistas a guardar
     * @param file Archivo JSON donde se guardarán los tenistas
     */
    override fun saveToFile(file: File, data: List<Tenista>) {
        val tenistasDto = data.map { it.fromTenistaToTenistaDto() }
        val format = Json { prettyPrint = true }
        val json = format.encodeToString(tenistasDto)
        logger.debug { "Guardando tenistas en archivo json: $file" }
        file.writeText(json)
        logger.debug { "Tenistas guardados en archivo json: ${tenistasDto.size}" }
    }
}

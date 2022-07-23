package controllers

import exceptions.RaquetaException
import models.Raqueta
import mu.KotlinLogging
import repositories.raquetas.RaquetasRepository
import services.raquetas.StorageRaquetasCsvService
import java.io.File
import java.util.*

private val logger = KotlinLogging.logger {}

/**
 * Controlador para gestión de Raquetas
 */
class RaquetasController(
    private val raquetasRepository: RaquetasRepository,
    private val storageRaquetasCsvService: StorageRaquetasCsvService
) {

    /**
     * Carga en la base de datos de raquetas los datos de un fichero CSV
     * @param csvFile Fichero CSV con los datos de las raquetas
     */
    fun importDataFromCsv(csvFile: File) {
        logger.debug { "Loading data from csv file $csvFile" }
        val raquetas = storageRaquetasCsvService.loadFromFile(csvFile)
        logger.debug { "Saving ${raquetas.size} raquetas" }
        raquetas.forEach { raqueta ->
            raquetasRepository.save(raqueta)
        }
        logger.debug { "Saved ${raquetas.size} raquetas" }
    }

    /**
     * Devuelve todas las raquetas
     * @return Lista de raquetas
     */
    fun getAll(): List<Raqueta> {
        logger.debug { "Getting all raquetas" }
        return raquetasRepository.findAll()
    }

    /**
     * Devuelve una raqueta por su id
     * @param id Id de la raqueta
     * @return Raqueta
     * @throws RaquetaException Si no existe la raqueta
     */
    fun getById(id: UUID): Raqueta {
        logger.debug { "Getting raqueta with id $id" }
        return raquetasRepository.findById(id) ?: throw RaquetaException("Raqueta with id $id not found")
    }

    /**
     * Inserta o Actualiza una nueva raqueta
     * @param raqueta Raqueta a insertar o actualizar si existe el id
     * @return Raqueta insertada o actualizada
     */
    fun save(raqueta: Raqueta): Raqueta {
        logger.debug { "Inserting raqueta $raqueta" }
        return raquetasRepository.save(raqueta)
    }

    /**
     * Elimina una raqueta
     * @param raqueta Raqueta a eliminar
     * @return Raqueta eliminada
     * @throws RaquetaException Si no existe la raqueta
     */
    fun delete(raqueta: Raqueta): Raqueta {
        logger.debug { "Deleting raqueta with id ${raqueta.id}" }
        return if (raquetasRepository.delete(raqueta)) {
            raqueta
        } else {
            throw RaquetaException("Raqueta with id ${raqueta.id} not found")
        }
    }

    /**
     * Obtiene una raqueta por su marca y modelo
     * @param marca Marca de la raqueta
     * @param modelo Modelo de la raqueta
     * @return Raqueta
     * @throws RaquetaException Si no existe la raqueta
     */
    fun getByMarcaAndModelo(marca: String, modelo: String): Raqueta {
        logger.debug { "Getting raqueta with marca $marca and modelo $modelo" }
        return raquetasRepository.findAll().firstOrNull {
            it.marca.lowercase().contains(marca.lowercase()) && it.modelo.lowercase().contains(modelo.lowercase())
        } ?: throw RaquetaException("Raqueta with marca $marca and modelo $modelo not found")
    }

    /**
     * Obtiene un informe de raquetas
     */
    fun report(): String {
        val sb = StringBuilder()
        val raquetas = raquetasRepository.findAll()
        sb.appendLine("Informe de raquetas:")
        sb.appendLine("-----------------------------------------")
        sb.appendLine("Total: ${raquetas.size}")
        sb.appendLine("Mas cara: ${raquetas.maxBy { it.precio }} ")
        sb.appendLine("Menos pesada: ${raquetas.minBy { it.peso }} ")
        sb.appendLine("Por marca: ${raquetas.groupBy { it.marca }} ")
        return sb.toString()
    }
}

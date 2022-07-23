package controllers

import exceptions.TenistaException
import models.Tenista
import mu.KotlinLogging
import repositories.tenistas.TenistasRepository
import services.tenistas.StorageTenistasCsvService
import services.tenistas.StorageTenistasJsonService
import utils.toLocalDate
import utils.toLocalMoney
import java.io.File
import java.time.LocalDate
import java.util.*


private val logger = KotlinLogging.logger {}

/**
 * Controlador de gestion de Tenistas
 */
class TenistasController(
    private val tenistasRepository: TenistasRepository,
    private val storageTenistasCsvService: StorageTenistasCsvService,
    private val storageTenistasJsonService: StorageTenistasJsonService,
) {

    /**
     * Carga la base de datos de tenistas desde un fichero CSV
     * @param csvFile Fichero CSV
     */
    fun importDataFromCsv(csvFile: File) {
        logger.debug { "Loading data from csv file $csvFile" }
        val tenistas = storageTenistasCsvService.loadFromFile(csvFile)
        logger.debug { "Saving ${tenistas.size} tenistas" }
        tenistas.forEach { tenista ->
            tenistasRepository.save(tenista)
        }
        logger.debug { "Saved ${tenistas.size} tenista" }
    }

    /**
     * Devuelve todos los tenistas
     * @return Lista de tenistas
     */
    fun getAll(): List<Tenista> {
        logger.debug { "Getting all tenistas" }
        return tenistasRepository.findAll()
    }

    /**
     * Devuelve un tenista por su id
     * @param id Id del tenista
     * @return Tenista
     */
    fun getById(id: UUID): Tenista {
        logger.debug { "Getting tenista with id $id" }
        return tenistasRepository.findById(id) ?: throw TenistaException("Tenista with id $id not found")
    }

    /**
     * Salva o actualiza un tenista
     * @param tenista Tenista
     * @return Tenista
     */
    fun save(tenista: Tenista): Tenista {
        logger.debug { "Saving tenista $tenista" }
        return tenistasRepository.save(tenista)
    }

    /**
     * Elimina un tenista
     * @param tenista Tenista
     * @return Tenista
     * @throws TenistaException Si no se puede eliminar el tenista
     */
    fun delete(tenista: Tenista): Tenista {
        logger.debug { "Deleting tenista $tenista" }
        return if (tenistasRepository.delete(tenista))
            tenista
        else
            throw TenistaException("Tenista with id ${tenista.id} not found")
    }

    /**
     * Exporta la base de datos de tenistas a un fichero JSON
     * @param jsonFile Fichero JSON
     */
    fun exportDataToJson(jsonFile: File) {
        logger.debug { "Exporting data to json file $jsonFile" }
        storageTenistasJsonService.saveToFile(jsonFile, getAll())
    }

    /**
     * Obtiene tenistas dado un nombre
     * @param name Nombre
     * @return Lista de tenistas
     */
    fun getByName(name: String): List<Tenista> {
        return tenistasRepository.findAll()
            .filter { it.nombre.lowercase().contains(name.lowercase()) }
    }

    /**
     * Obtiene los tenistas que llevan una marca de Raqueta
     * @param marca Marca de Raqueta
     * @return Lista de tenistas
     */
    fun getByMarcaRaqueta(marcaRaqueta: String): List<Tenista> {
        return tenistasRepository.findAll()
            .filter { it.raqueta?.marca?.lowercase()?.contains(marcaRaqueta.lowercase()) ?: false }
    }

    /**
     * Obtiene un informe de la base de datos de tenistas
     */
    fun report(): String {
        val sb = StringBuilder()
        val tenistas = tenistasRepository.findAll()
        sb.appendLine("Informe de tenistas:")
        sb.appendLine("-----------------------------------------")
        sb.appendLine("Total: ${tenistas.size}")
        sb.appendLine("Ordenados por puntuación: ${tenistas.sortedByDescending { it.puntos }}")
        sb.appendLine("Ordenados por altura: ${tenistas.sortedByDescending { it.altura }}")
        sb.appendLine("Agrupados por Reves: ${tenistas.groupBy { it.tipoReves }}")
        sb.appendLine("Agrupados por Raqueta: ${tenistas.groupBy { it.raqueta?.marca }}")
        // Loo ideal es salvar en un avariable para no repetir varias veces la misma consulta
        val res = tenistas.groupBy { it.raqueta?.marca }.maxBy { it.value.size }
        sb.appendLine(
            "Raqueta más usada: ${res.key} con ${res.value.size} tenistas"
        )
        sb.appendLine("Numero de tenistas zurdos: ${tenistas.count { it.manoDominante == Tenista.ManoDominante.IZQUIERDA }}")
        sb.appendLine("Ganancia media: ${tenistas.map { it.ganancias }.average().toLocalMoney()}")
        // Loo ideal es salvar en un avariable para no repetir varias veces la misma consulta
        val joven = tenistas.maxBy { it.fechaNacimiento }
        sb.appendLine(
            "Tenista más joven: ${joven.nombre} nacido el ${joven.fechaNacimiento.toLocalDate()} " +
                    "y tiene ${(LocalDate.now().minusYears(joven.fechaNacimiento.year.toLong())).year} años"
        )
        // Tenistas con mas de 30 años
        val mayores30 = tenistas.filter { LocalDate.now().minusYears(it.fechaNacimiento.year.toLong()).year > 30 }
        sb.appendLine("Tenistas mayores de 30 años: ${mayores30.size} y son ${mayores30.map { it.nombre }}")

        return sb.toString()
    }

}

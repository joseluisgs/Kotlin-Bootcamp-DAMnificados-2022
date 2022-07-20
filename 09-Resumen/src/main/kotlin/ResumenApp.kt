import config.AppConfig
import mu.KotlinLogging
import org.koin.core.component.KoinComponent
import services.raquetas.StorageRaquetasCsvService
import services.raquetas.StorageRaquetasCsvServiceImpl
import services.tenistas.StorageTenistasCsvService
import services.tenistas.StorageTenistasCsvServiceImpl
import services.tenistas.StorageTenistasJsonService
import services.tenistas.StorageTenistasJsonServiceImpl
import java.io.File

/**
 * Clase de de la Aplicación
 */
private val logger = KotlinLogging.logger {}

const val APP_PROPERTIES = "src/main/resources/config.properties"
const val TENISTAS_INPUT_CSV_FILE = "data/tenistas.csv"
const val RAQUETAS_INPUT_CSV_FILE = "data/raquetas.csv"
const val RAQUETAS_OUTPUT_CSV_FILE = "data/raquetas-output.csv"
const val TENISTAS_OUTPUT_CSV_FILE = "data/tenistas-output.csv"
const val TENISTAS_INPUT_JSON_FILE = "data/tenistas.json"
const val TENISTAS_OUTPUT_JSON_FILE = "data/tenistas-output.json"

class ResumenApp : KoinComponent {
    var appConfig: AppConfig = AppConfig.fromPropertiesFile(APP_PROPERTIES)

    /**
     * Método principal de la aplicación
     */
    fun run() {
        println("\uD83D\uDC4B Hola Resumen")
        println("===============")

        jugandoConStorage()
    }

    /**
     * Jugando con los servicios de Storage
     */
    private fun jugandoConStorage() {
        val raquetasCsvStorage: StorageRaquetasCsvService = StorageRaquetasCsvServiceImpl()
        val raquetas = raquetasCsvStorage.loadFromFile(File(RAQUETAS_INPUT_CSV_FILE))
        println("Raquetas: $raquetas")

        val tenistasCsvStorage: StorageTenistasCsvService = StorageTenistasCsvServiceImpl()
        var tenistas = tenistasCsvStorage.loadFromFile(File(TENISTAS_INPUT_CSV_FILE))
        println("Tenistas: $tenistas")

        raquetasCsvStorage.saveToFile(File(RAQUETAS_OUTPUT_CSV_FILE), raquetas.sortedBy { it.marca })
        tenistasCsvStorage.saveToFile(File(TENISTAS_OUTPUT_CSV_FILE), tenistas.sortedBy { it.ranking })

        val tenistasJsonStorage: StorageTenistasJsonService = StorageTenistasJsonServiceImpl()
        tenistasJsonStorage.saveToFile(File(TENISTAS_OUTPUT_JSON_FILE), tenistas.sortedBy { it.ranking })
        tenistas = tenistasJsonStorage.loadFromFile(File(TENISTAS_INPUT_JSON_FILE))
        println("Tenistas: $tenistas")
    }
}
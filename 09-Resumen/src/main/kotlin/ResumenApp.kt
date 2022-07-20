import config.AppConfig
import mu.KotlinLogging
import org.koin.core.component.KoinComponent
import services.raquetas.StorageRaquetasCsvService
import services.raquetas.StorageRaquetasCsvServiceImpl
import services.tenistas.StorageTenistasCsvService
import services.tenistas.StorageTenistasCsvServiceImpl

/**
 * Clase de de la Aplicación
 */
private val logger = KotlinLogging.logger {}

const val APP_PROPERTIES = "src/main/resources/config.properties"
const val TENISTAS_INPUT_CSV_FILE = "data/tenistas.csv"
const val RAQUETAS_INPUT_CSV_FILE = "data/raquetas.csv"
const val RAQUETAS_OUTPUT_FILE = "data/raquetas-output.csv"
const val TENISTAS_OUTPUT_FILE = "data/tenistas-output.csv"

class ResumenApp : KoinComponent {
    var appConfig: AppConfig = AppConfig.fromPropertiesFile(APP_PROPERTIES)

    /**
     * Método principal de la aplicación
     */
    fun run() {
        println("\uD83D\uDC4B Hola Resumen")
        println("===============")

        // Creamos los servicios que necesitamos
        val raquetasCsvStorage: StorageRaquetasCsvService = StorageRaquetasCsvServiceImpl()
        val raquetas = raquetasCsvStorage.loadFromFile(RAQUETAS_INPUT_CSV_FILE)
        println("Raquetas: $raquetas")
        val tenistasCsvStorage: StorageTenistasCsvService = StorageTenistasCsvServiceImpl()
        val tenistas = tenistasCsvStorage.loadFromFile(TENISTAS_INPUT_CSV_FILE)
        println("Tenistas: $tenistas")
        raquetasCsvStorage.saveToFile(RAQUETAS_OUTPUT_FILE, raquetas)
    }
}
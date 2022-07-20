import config.AppConfig
import mu.KotlinLogging
import org.koin.core.component.KoinComponent
import services.raquetas.StorageRaquetasCsvService
import services.raquetas.StorageRaquetasCsvServiceImpl
import services.tenistas.StorageTenistasCsvService
import services.tenistas.StorageTenistasCsvServiceImpl
import java.io.File

/**
 * Clase de de la Aplicación
 */
private val logger = KotlinLogging.logger {}

const val APP_PROPERTIES = "src/main/resources/config.properties"

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
        val raquetas = raquetasCsvStorage.loadFromFile("data${File.separator}raquetas.csv")
        println("Raquetas: $raquetas")
        val tenistasCsvStorage: StorageTenistasCsvService = StorageTenistasCsvServiceImpl()
        val tenistas = tenistasCsvStorage.loadFromFile("data${File.separator}tenistas.csv")
        println("Tenistas: $tenistas")
    }
}
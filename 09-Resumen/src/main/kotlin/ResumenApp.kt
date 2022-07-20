import config.AppConfig
import mu.KotlinLogging
import org.koin.core.component.KoinComponent

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

        // Leemos la configuracón del proyecto

    }
}
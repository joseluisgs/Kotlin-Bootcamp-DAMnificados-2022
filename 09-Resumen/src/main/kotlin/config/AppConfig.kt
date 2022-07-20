package config

import mu.KotlinLogging
import java.io.FileInputStream
import java.util.*

private val logger = KotlinLogging.logger {}

/**
 * Clase para la lectura de los parámetros de configuración.
 * @author JoseLuisGS
 */
data class AppConfig(
    val nombre: String,
    val version: String,
    val jdbcUrl: String,
    val jdbcUserName: String,
    val jdbcPassword: String,
    val jdbcDriverClassName: String,
    val jdbcMaximumPoolSize: Int = 10,
    val jdbcCreateTables: Boolean = true,
    val jdbcshowSQL: Boolean = true,
) {
    companion object {
        // Configuración por defecto
        val DEFAULT = AppConfig(
            nombre = "app",
            version = "1.0.0",
            jdbcUrl = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;",
            jdbcUserName = "sa",
            jdbcPassword = "",
            jdbcDriverClassName = "org.h2.Driver",
            jdbcMaximumPoolSize = 10,
            jdbcCreateTables = true,
            jdbcshowSQL = true,
        )

        /**
         * Lee el fichero de configuración y devuelve un objeto con los parámetros de configuración.
         * @param fileName String que representa el Fichero de configuración.
         * @return AppConfig con los parámetros de configuración.
         */
        fun fromPropertiesFile(fileName: String): AppConfig {
            logger.debug { "Leyendo propiedades desde: $fileName" }
            if (!FileInputStream(fileName).use { it.available() > 0 }) {
                logger.error { "File not found: $fileName" }
                throw IllegalArgumentException("Fichero de configuración no encontrado: $fileName")
            }
            val properties = Properties()
            properties.load(FileInputStream(fileName))
            logger.debug { "Propiedades leídas: $properties" }
            return AppConfig(
                nombre = properties.getProperty("nombre") ?: DEFAULT.nombre,
                version = properties.getProperty("version") ?: DEFAULT.version,
                jdbcUrl = properties.getProperty("jdbc.url") ?: DEFAULT.jdbcUrl,
                jdbcUserName = properties.getProperty("jdbc.username") ?: DEFAULT.jdbcUserName,
                jdbcPassword = properties.getProperty("jdbc.password") ?: DEFAULT.jdbcPassword,
                jdbcDriverClassName = properties.getProperty("jdbc.driverClassName") ?: DEFAULT.jdbcDriverClassName,
                jdbcMaximumPoolSize = properties.getProperty("jdbc.maximumPoolSize").toIntOrNull()
                    ?: DEFAULT.jdbcMaximumPoolSize,
                jdbcCreateTables = properties.getProperty("jdbc.createTables").toBooleanStrictOrNull()
                    ?: DEFAULT.jdbcCreateTables,
                jdbcshowSQL = properties.getProperty("jdbc.showSQL").toBooleanStrictOrNull() ?: DEFAULT.jdbcshowSQL,
            )
        }
    }
}
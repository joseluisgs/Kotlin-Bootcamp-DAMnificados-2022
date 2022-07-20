package config

import mu.KotlinLogging
import java.io.File
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
        val DEFAULT by lazy {
            AppConfig(
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
        }

        /**
         * Lee el fichero de configuración y devuelve un objeto con los parámetros de configuración.
         * @param file Fichero de configuración.
         * @return AppConfig con los parámetros de configuración.
         */
        fun fromPropertiesFile(file: File): AppConfig {
            logger.debug { "Leyendo propiedades desde: $file" }
            if (!file.exists() || !FileInputStream(file).use { it.available() > 0 }) {
                logger.error { "File not found: $file" }
                throw IllegalArgumentException("Fichero de configuración no encontrado o incorrecto: $file")
            }
            val properties = Properties()
            properties.load(FileInputStream(file))
            logger.debug { "Propiedades leídas: $properties" }
            return AppConfig(
                nombre = properties.getProperty("nombre") ?: DEFAULT.nombre,
                version = properties.getProperty("version") ?: DEFAULT.version,
                jdbcUrl = properties.getProperty("jdbc.url") ?: DEFAULT.jdbcUrl,
                jdbcUserName = properties.getProperty("jdbc.username") ?: DEFAULT.jdbcUserName,
                jdbcPassword = properties.getProperty("jdbc.password") ?: DEFAULT.jdbcPassword,
                jdbcDriverClassName = properties.getProperty("jdbc.driverClassName") ?: DEFAULT.jdbcDriverClassName,
                jdbcMaximumPoolSize = properties.getProperty("jdbc.maximumPoolSize")?.toIntOrNull()
                    ?: DEFAULT.jdbcMaximumPoolSize,
                jdbcCreateTables = properties.getProperty("jdbc.createTables")?.toBooleanStrictOrNull()
                    ?: DEFAULT.jdbcCreateTables,
                jdbcshowSQL = properties.getProperty("jdbc.showSQL")?.toBooleanStrictOrNull() ?: DEFAULT.jdbcshowSQL,
            )
        }
    }
}
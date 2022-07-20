package config

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.File

internal class AppConfigTest {
    private val config = AppConfig(
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

    private val file = "src/test/resources/config.properties"

    @Test
    fun default() {
        val default = AppConfig.DEFAULT

        assert(config == default)
    }

    @Test
    fun fromPropertiesFile() {
        val loaded = AppConfig.fromPropertiesFile(File(file))

        assert(config == loaded)
    }

    @Test
    fun fromPropertiesFileException() {
        val ex = assertThrows<IllegalArgumentException> {
            AppConfig.fromPropertiesFile(File("error.properties"))
        }

        assert(ex.message == "Fichero de configuración no encontrado o incorrecto: error.properties")
    }

}
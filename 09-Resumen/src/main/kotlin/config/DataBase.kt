package config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import entities.RaquetasTable
import entities.TenistasTable
import mu.KotlinLogging
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

private val logger = KotlinLogging.logger {}

/**
 * Clase que se encarga de inicializar la base de datos en base a su configuracion.
 * Singleton
 */
object DataBase {
    lateinit var appConfig: AppConfig
    fun init(appConfig: AppConfig) {
        this.appConfig = appConfig
        logger.debug { "Inicializando base de datos" }
        // Aplicamos Hiraki para la conexión a la base de datos
        val hikariConfig = HikariConfig().apply {
            jdbcUrl = appConfig.jdbcUrl
            driverClassName = appConfig.jdbcDriverClassName
            username = appConfig.jdbcUserName
            password = appConfig.jdbcPassword
            maximumPoolSize = appConfig.jdbcMaximumPoolSize
        }

        val dataSource = HikariDataSource(hikariConfig)

        /*
        Sin Hikari
        Database.connect(
            url = appConfig.jdbcUrl,
            driver = appConfig.jdbcDriverClassName,
            user = appConfig.jdbcUserName,
            password = appConfig.jdbcPassword
        )
        */

        Database.connect(dataSource)
        logger.debug { "Bases de datos iniciada exitosamente" }

        if (appConfig.jdbcCreateTables) {
            createTables()
        }
    }

    /**
     * Crea las tablas en la base de datos
     */
    private fun createTables() = transaction {
        logger.debug { "Creando tablas de la base de datos" }

        if (appConfig.jdbcshowSQL)
            addLogger(StdOutSqlLogger) // Para que se vea el log de consulas a la base de datos

        // Mis tablas
        val tables = arrayOf(
            RaquetasTable,
            TenistasTable
        )

        SchemaUtils.create(*tables)
        logger.debug { "Tablas creadas (${tables.size}): ${tables.joinToString { it.tableName }}" }
    }

    /**
     * Elimina las tablas de la base de datos
     */
    private fun dropTables() = transaction {
        logger.debug { "Eliminando tablas de la base de datos" }

        if (appConfig.jdbcshowSQL)
            addLogger(StdOutSqlLogger) // Para que se vea el log de consulas a la base de datos

        // Mis tablas
        val tables = arrayOf(
            RaquetasTable,
            TenistasTable
        )

        SchemaUtils.drop(*tables)
        logger.debug { "Tablas eliminadas (${tables.size}): ${tables.joinToString { it.tableName }}" }
    }

    /**
     * Vacia las tablas de la base de datos
     */
    private fun clearTables() = transaction {
        logger.debug { "Limpiando tablas de la base de datos" }

        if (appConfig.jdbcshowSQL)
            addLogger(StdOutSqlLogger) // Para que se vea el log de consulas a la base de datos

        // Mis tablas
        val tables = arrayOf(
            RaquetasTable,
            TenistasTable
        )

        tables.forEach {
            it.deleteAll()
        }
        logger.debug { "Tablas limpiadas (${tables.size}): ${tables.joinToString { it.tableName }}" }
    }
}
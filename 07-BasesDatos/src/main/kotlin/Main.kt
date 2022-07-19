import config.AppConfig
import entities.*
import managers.DataBaseManager
import model.Raqueta
import model.Tenista
import org.jetbrains.exposed.sql.SizedCollection
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import repositories.raqueta.RaquetasRepository
import repositories.raqueta.RaquetasRepositoryImpl
import repositories.tenista.TenistasRepository
import repositories.tenista.TenistasRepositoryImpl
import java.time.LocalDate

/**
 * Fundamental leer:
 * https://github.com/JetBrains/Exposed/wiki
 * https://www.baeldung.com/kotlin/exposed-persistence
 * Puedes manejarlo como DSL o como DAO
 *
 * Yo soy muy fan de la forma DAO por hacerme un ORM :)
 */

fun main() {
    println("Hola Bases de Datos")

    // Leemos la configuración del fichero de propiedades
    val appConfig = AppConfig.fromPropertiesFile("src/main/resources/config.properties")
    println("Configuración: $appConfig")

    // Iniciamos la base de datos con la configuracion que hemos leido
    DataBaseManager.init(appConfig)

    // daoRaquetas()

    repositorios()
}

fun repositorios() {
    // Vamos a crear varias raquetas
    var babolatAero = Raqueta(marca = "Babolat Aero", precio = 195.0)
    var babolatDrive = Raqueta(marca = "Babolat Pure Drive", precio = 185.50)
    var headSpeed = Raqueta(marca = "Head Speed", precio = 199.0)
    var headBoom = Raqueta(marca = "Head Boom", precio = 170.0)

    // Repositorio de raquetas
    val raquetasRepository: RaquetasRepository = RaquetasRepositoryImpl(RaquetaDao /*TenistaDao*/)

    // Insertamos raquetas
    babolatAero = raquetasRepository.save(babolatAero)
    babolatDrive = raquetasRepository.save(babolatDrive)
    headSpeed = raquetasRepository.save(headSpeed)
    headBoom = raquetasRepository.save(headBoom)

    println("Babolat Aero: $babolatAero")

    // Obtenemos todas las raquetas
    var raquetas = raquetasRepository.findAll()
    println("Raquetas: $raquetas")

    // Actualizamos head boom
    headBoom.apply {
        marca = "Head Boom updated"
        precio = 200.0
    }
    raquetasRepository.save(headBoom)

    // Buscamos head boom
    val headBoomUpdated = raquetasRepository.findById(headBoom.id)
    println("Head Boom updated: $headBoomUpdated")

    // Borramos head boom
    raquetasRepository.delete(headBoom)

    // Obtenemos todas las raquetas
    raquetas = raquetasRepository.findAll()
    println("Raquetas: $raquetas")


    // Vamos con los tenistas
    val tenistasRepository: TenistasRepository = TenistasRepositoryImpl(TenistaDao, RaquetaDao)

    var nadal = Tenista(
        nombre = "Rafael Nadal",
        ranking = 3,
        fechaNacimiento = LocalDate.parse("1985-06-04"),
        añoProfesional = 2005,
        altura = 185,
        peso = 80,
        ganancias = 10000000.0,
        manoDominante = Tenista.ManoDominante.from("Izquierda"),
        tipoReves = Tenista.TipoReves.from("dos manos"),
        puntos = 6789,
        raqueta = babolatAero
    )

    nadal = tenistasRepository.save(nadal)
    println("Tenista nadal: $nadal")

    // tenistas con babolat aero... Esto no es necsario hacerlo, porque podemos hacerlo filtrando....
    /*val tenistas = raquetasRepository.getTenistas(babolatAero)
    println("Tenistas con Babolat Aero: $tenistas")*/

    val tenistasAero = tenistasRepository.findAll()
        .filter { it.raqueta == babolatAero } // También lo podríamos hacer con un método en el repositorio por ejemplo finByRaquet
    println("Tenistas con Babolat Aero: $tenistasAero")

    // Actualizamos nadal
    nadal.apply {
        nombre = "Rafael Nadal updated"
        ranking = 4
        raqueta = babolatDrive
    }

    tenistasRepository.save(nadal)
    println("Tenistas nadal: $nadal")

    // Borramos nadal
    tenistasRepository.delete(nadal)

    // Obtenemos todos los tenistas
    val tenistas = tenistasRepository.findAll()
    println("Tenistas: $tenistas")

}

fun daoRaquetas() {
    // Los DAO son los Data Access Object, que son los que nos permiten acceder a la base de datos y mapearlos
    // Kotlin tiene DSL con Exposed, por lo que podemos consular y hacer todo lo que queramos con los DAO

    // Vamos hacer un CRUD de la tabla raqueta, lo voy a meter en el contexto de una transacción
    transaction {
        // addLogger(StdOutSqlLogger) // Muestra las sentencias SQL que se ejecutan pero ya las vemos en logger

        val babolatAero = RaquetaDao.new {
            this.marca = "Babolat Pure Aero"
            this.precio = 190.0
        }
        val babolatDrive = RaquetaDao.new {
            this.marca = "Babolat Pure Drive"
            this.precio = 185.50
        }
        val headSpeed = RaquetaDao.new {
            this.marca = "Head Speed"
            this.precio = 199.99
        }
        RaquetaDao.new {
            this.marca = "Head Boom"
            this.precio = 170.0
        }

        // Tambien puedo consuoltar todo el contenido de la tabla raqueta
        val raquetas = RaquetaDao.all() // ya pudo tratarlo
        println("Todas las raquetas: ${raquetas.joinToString(separator = "\n") { it.marca }}")
        val raqueta = RaquetaDao.find { RaquetasTable.marca eq "Head Boom" }.first() // ya pudo tratarlo
        println("Raqueta con marca Head Boom: ${raqueta.marca}")

        // Vamos a cambiael el precio de la raqueta con marca Head Boom
        println("Precio antes de cambiar: ${raqueta.precio}")
        raqueta.precio = 199.99 // Ya ta!!!!!
        println("Precio despues de cambiar: ${raqueta.precio}")

        raqueta.apply {
            marca = "Head Boom Updated"
            precio = 199.99
        } // Ya ta!!!!!

        // Vamos a obtener todas las raquetas y mostrar el precio
        RaquetaDao.all().sortedByDescending { it.precio }
            .forEach { println("Raqueta ${it.marca} tiene un precio de ${it.precio}") }

        // Vamos a borrar la raqueta con marca Head Boom
        raqueta.delete() // Ya ta!!!!!
        println("Raqueta con marca Head Boom borrada")
        RaquetaDao.all().forEach { println("Raqueta ${it.marca} tiene un precio de ${it.precio}") }

        // Vamos a añadir un tenista a babolat Aero
        //vamos a crear un tenista y añadirle la babolatAero
        // no hace falta poner el this
        val nadal = TenistaDao.new {
            this.nombre = "Rafael Nadal"
            this.ranking = 3
            this.fechaNacimiento = LocalDate.parse("1985-06-04")
            this.añoProfesional = 2005
            this.altura = 185
            this.peso = 80
            this.ganancias = 10000000.0
            this.manoDominante = "IZQUIERDA"
            this.tipoReves = "DOS MANOS"
            puntos = 6789
            this.raqueta = babolatAero
        }

        // Relaciones bidireccionales !! :)
        // Si ahora consultamos los tenistas...
        TenistaDao.all().forEach { println("Tenista ${it.nombre} tiene la raqueta ${it.raqueta.marca}") }
        // Vamos a ver los tenistas que llevan la aero
        babolatAero.tenistas.forEach { println("Tenista ${it.nombre} tiene la raqueta ${it.raqueta.marca}") }

        // Puedo usar incluso filter
        val tenistas = babolatAero.tenistas.filter { it.nombre.contains("Nadal") }
        println(
            "Tenistas que llevan la raqueta ${babolatAero.marca} que contienen la palabra Nadal: ${
                tenistas.joinToString(
                    separator = "\n"
                ) { it.nombre }
            }"
        )

        RaquetaDao.all().filter { it.marca.contains("Babolat") }
            .forEach { println("Raqueta ${it.marca} tiene ${it.tenistas.count()} tenistas") }

        // o usar funciones mas SQL
        RaquetaDao.find { RaquetasTable.marca.like("%Babolat%") }
            .forEach { println("Raqueta ${it.marca} tiene ${it.tenistas.count()} tenistas") }

        RaquetaDao.find { RaquetasTable.precio greaterEq 185 }
            .forEach { println("Raqueta ${it.marca} tiene un precio ${it.precio}") }


        // tamnbien podemos usar las cosa sin el DSL y DAO usando las Tables directamente
        RaquetasTable.insert {
            it[marca] = "Babolat Pure Aero VS"
            it[precio] = 190.0
        }

        // Podemos hacer torneos
        val torneo = TorneoDao.new {
            this.nombre = "Torneo de tenis"
            this.fecha = LocalDate.parse("2022-07-19")
            this.premio = 1000000.0
        }

        println("Torneo creado: ${torneo.nombre}")
        // Le añadimos los tenistas a el torneo
        torneo.tenistas = SizedCollection(listOf(nadal))

        // Vemos los tenistas del torneo
        println("Tenistas del torneo: ${torneo.tenistas.joinToString(separator = "\n") { it.nombre }}")

        // También podemos ver los torneos de un tenista
        nadal.torneos.forEach { println("Tenista ${it.nombre} tiene el torneo ${it.nombre}") }

        // nadal.torneos = SizedCollection(listOf(torneo)) Lo dejo con val para que no se puedan asignar torneos desde tenista...

        /// Lo podemos borrar todo
        TorneosTenistasTable.deleteAll()
        TenistasTable.deleteAll()
        RaquetasTable.deleteAll()
        println("Todos los tenistas y raquetas borrados")
        println("Todas las raquetas: ${RaquetaDao.all().joinToString(separator = "\n") { it.marca }}")
        println(babolatAero.marca)

        // Investiga con torneos y tenistas!!!!
    }

}

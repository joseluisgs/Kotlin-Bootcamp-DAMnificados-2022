import repositories.TenistasRepository
import java.io.File

fun main() {
    println("Hola ficheros!")
    // Lo voy a meter en un repositorio
    val repo = TenistasRepository()

    // Leo un CSV y trasformo en fichero en una lista de tenistas
    println("Leyendo fichero CSV...")
    repo.loadFromCsv("data${File.separator}tenistas.csv")

    // Muestro la lista de tenistas
    println("Tenistas:")
    repo.getTenistas().forEach { println(it) }

    // Guardo en un fichero JSON
    println("Guardando fichero JSON...")
    repo.saveToJson("data${File.separator}tenistas.json")

    // Leemomos de un fichero JSON
    println("Leyendo fichero JSON...")
    repo.loadFromJson("data${File.separator}tenistas.json")

    // salvamos en un fichero CSV
    println("Guardando fichero CSV...")
    repo.saveToCsv("data${File.separator}tenistas-2.csv")

    println("Leyendo fichero CSV 2...")
    repo.loadFromCsv("data${File.separator}tenistas-2.csv")

    // Muestro la lista de tenistas
    println("Tenistas 2:")
    repo.getTenistas().forEach { println(it) }

}
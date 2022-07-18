fun main() {
    var nombre = ""
    var edad = -1

    // Pedir el nombre
    do {
        println("Dime tu nombre: ")
        nombre = readln()
    } while (nombre.isEmpty())

    // Pedir edad
    do {
        println("Dime tu edad: ")
        edad = readln().toIntOrNull() ?: -1
    } while (edad < 0)

    println("Hola $nombre tienes $edad")
}
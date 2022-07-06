package inicio

// Todos sabemos lo que es una función
// fragmentos de código, que aceptar parámetros de entrada (o no),
// realizan una serie de acciones y devuelve un valor (o no).

fun suma(a: Int, b: Int): Int {
    return a + b
}

fun saludar(nombre: String) {
    println("Hola $nombre")
}

fun doNothig() {
    println("No hago nada")
}

fun main() {
    val resultado = suma(1, 2)
    println(resultado)
    saludar("Juan")
    doNothig()
}
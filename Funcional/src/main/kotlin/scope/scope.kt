package scope// Las funciones scope de Kotlin
// Nos permiten realizar acciones sobre el objeto que estamos trabajando
// y varían si el propio objeto es el receptos del mismo
// Es una combinación de todo lo visto

// no hay una regla para ellas y a veces depende del gusto del programador

fun main() {
    // Run
    // nos permite inicializar y ejecutar un método del objeto, el propio objeto es el receptor
    var p1 = Persona()
    p1.run {
        nombre = "Juan"
        edad = 25
        saludar()
    }

    // With, nos permite cambiar el estado de un objeto, hay que indicarle el objeto
    var p2 = Persona()
    with(p2) {
        nombre = "Juan"
        edad = 25
    }
    p2.saludar()

    // Apply nos permite cambiar el estado de un objeto, el propio objeto es el receptor: Anda la interfaz fluida
    var p3 = Persona()
    p3.apply {
        nombre = "Juan"
        edad = 25
    }.saludar()

    // Let se suele usar para comparaciones y realizar una accion, el propio objeto es el receptor (adios if!!)
    var p4 = Persona()
    p4.let {
        it.saludar()
    }

    var p5: Persona? = null
    p5?.let {
        it.nombre = "Juan"
        it.saludar()
    }

    // Also se usa para realizar una accion, posterior
    var p6 = Persona()
    p2.apply {
        nombre = "Juan"
        edad = 25
    }.also {
        it.saludar()
    }
}

package koin.cafeteras

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

class CafeterasApp : KoinComponent {
    val cafetera: Cafetera by inject() // podriamos usar get()

    fun run() {
        println("Ejemplo de Cafeteras Koin")
        println("===========================")
        println("Cafetera: $cafetera")
        cafetera.servir()
        println(cafetera)
        println()

        val cafetera2: Cafetera by inject() // podriamos usar get()
        println("Cafetera2: $cafetera2")
        cafetera2.servir()
    }
}

fun main() {
    // Comenzamos con Koin
    startKoin {
        // use Koin logger
        printLogger()
        // declare modules
        modules(CafeterasModule,)
    }
    CafeterasApp().run()
}
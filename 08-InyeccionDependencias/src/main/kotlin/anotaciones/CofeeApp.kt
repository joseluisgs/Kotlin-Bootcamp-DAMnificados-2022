package anotaciones

import anotaciones.component.Cafetera
import anotaciones.di.CoffeeAppModule
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.GlobalContext.startKoin
import org.koin.ksp.generated.module

// Anotaciones: https://insert-koin.io/docs/reference/koin-annotations/annotations

class CoffeeApp : KoinComponent {
    private val cafetera: Cafetera by inject() //= get()

    fun run() {
        println("Ejemplo de Cafeteras Koin Annotations")
        println("===========================")
        println("Cafetera: $cafetera")
        cafetera.servir()
        println(cafetera)
        println()
    }
}

fun main() {
    startKoin {
        modules(CoffeeAppModule().module)
    }
    CoffeeApp().run()
}
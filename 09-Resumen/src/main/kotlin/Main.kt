import org.koin.core.context.GlobalContext.startKoin

/**
 * Modulo Main
 */
fun main() {
    // Lanzamos la configuración de Koin
    startKoin {
        // Modulos de Koin
    }

    // ejecutamos nuestra App
    ResumenApp().run()
}
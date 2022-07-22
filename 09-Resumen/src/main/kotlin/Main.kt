import org.koin.core.context.GlobalContext.startKoin

/**
 * Modulo Main
 */
fun main() {
    // Lanzamos la configuración de Koin
    startKoin {
        // Modulos de Koin
        //modules(ResumenAppDI().module)
        // defaultModule()
        modules(AppModuleDI)
    }

    // ejecutamos nuestra App
    ResumenApp().run()
}
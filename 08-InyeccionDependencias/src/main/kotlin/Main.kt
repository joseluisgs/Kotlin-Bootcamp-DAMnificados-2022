import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

// https://insert-koin.io/docs/reference/koin-core/dsl


class HelloMessageData {
    val message: String = "Hola Koin!"
}

interface HelloService {
    fun hello(): String
}

class HelloServiceImpl(private val helloMessageData: HelloMessageData) : HelloService {
    override fun hello() = "Hey, ${helloMessageData.message}"
}

class HelloApplication : KoinComponent {

    // Obtenemos el servicio con sus dependencias de manera perezosa
    val helloService by inject<HelloService>()  // = get() resuleve de manera directa

    // mostramos el mensaje
    fun sayHello() = println(helloService.hello())
}

//  Crea el módulo que Koin usa para proveer todas las dependencias.
val helloModule = module {
// Forma classica de inyectar una dependencia
    // Nos ofrece la dependencia como singleton, es decir, siempre la misma instancia del objeto cada vez que sea inyectada
    single { HelloMessageData() }
    single<HelloService> { HelloServiceImpl(get()) }

    // https://insert-koin.io/docs/reference/koin-core/dsl-update (Otra forma)

    //singleOf(::HelloMessageData)
    // Resuleve como HelloServiceImpl, cuando se necesita la dependencia HelloService, get obtiene la depedencia de manera directa, podemos ponerle
    //singleOf(::HelloServiceImpl) { bind<HelloService>() }
}

fun main(vararg args: String) {
    // Crea una instancia de Koin y registra su contexto...
    startKoin {
        // usamos el lloger de Koin
        printLogger()
        // declaramos los modulos, es decir, donde se definen qué dependencias y cómo se resuleven...
        modules(helloModule)
    }

    HelloApplication().sayHello()
}

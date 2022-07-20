package koin.myviews

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import java.util.*

class MyView : KoinComponent {
    private val id: UUID = UUID.randomUUID()

    // incluso podemos definirlo como Lazy e inyectarlo en el init...
    private var presenter: Lazy<Presenter>

    init {
        presenter = inject()
    }

    override fun toString(): String {
        return "MyView(id=$id, presenter=${presenter.value})"
    }

    fun run() {
        println("Ejemplo MyView - Presenter - Navigator")
        println("========================================")
        println("MyView: $this")
        println()
    }
}

fun main() {
    // Comenzamos con Koin
    startKoin {
        // use Koin logger
        printLogger()
        // declare modules
        modules(MyViewsModule)
    }
    MyView().run()
}

import controllers.ProcesosController
import models.Proceso
import repositories.ColaProcesosPrioritariaImp

fun main() {
    println("Hello, world!")

    val controller = ProcesosController(ColaProcesosPrioritariaImp())
    (1..10).forEach {
        controller.push(Proceso(nombre = "Proceso $it", prioridad = it))
    }

    controller.getAll().forEach { println(it) }

    println(controller.size())

    println(controller.isVacia())

    controller.getAll().forEach { _ -> println(controller.pop()) }

    println(controller.size())

    println(controller.isVacia())


}
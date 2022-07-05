import models.Persona
import models.PersonaJ

fun main() {
    val p1 = Persona()
    println(p1.nombre)
    p1.edad = 20
    println(p1.show())

    val p2 = PersonaJ()
    println(p2.nombre)
    p2.edad = 20
    println(p2.show())

}
import models.Persona
import models.PersonaJ

fun main() {
    println("KOTLIN")
    val p1 = Persona()
    println(p1.nombre)
    p1.edad = 20
    println(p1.show())
    p1.edad = -1
    println(p1.edad)
    p1.edad = 10
    println(p1.edad)

    println("JAVA")
    val p2 = PersonaJ()
    println(p2.nombre)
    p2.edad = 20
    println(p2.show())
    p2.edad = -1
    println(p2.edad)
    p2.edad = 10
    println(p2.edad)

}
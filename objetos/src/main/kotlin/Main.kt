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

    val p3 = PersonaJ("Pepe", 20)
    println(p3.show())
    val p5 = Persona(edad = 20)
    val p6 = Persona(nombre = "Prueba")
    println(p6.isMayorDeEdad)

    println("JAVA")
    val p2 = PersonaJ()
    println(p2.nombre)
    p2.edad = 20
    println(p2.show())
    p2.edad = -1
    println(p2.edad)
    p2.edad = 10
    println(p2.edad)

    val p4 = PersonaJ("Pepe", 20)
    println(p4.show())

}
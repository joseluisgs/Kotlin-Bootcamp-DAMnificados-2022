package models

open class Programador(nombre: String, edad: Int, var experiencia: Int = 0) : Persona(nombre, edad) {
    override fun toString(): String {
        return "Programador(nombre='$nombre', edad=$edad, experiencia=$experiencia)"
    }
}
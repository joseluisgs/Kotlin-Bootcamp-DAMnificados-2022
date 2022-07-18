package models

class ProgramadorKotlin(nombre: String, edad: Int, experiencia: Int) : Programador(nombre, edad, experiencia), IKotlin {
    override fun disfrutar() {
        println("$nombre esta disfrutando de Kotlin")
    }

}

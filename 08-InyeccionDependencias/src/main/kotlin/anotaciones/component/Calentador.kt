package anotaciones.component

interface Heater {
    fun encender()
    fun apagar()
    fun estaCaliente(): Boolean
}
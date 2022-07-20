package koin.cafeteras

interface Calentador {
    fun encender()
    fun apagar()
    fun estaCaliente(): Boolean
}

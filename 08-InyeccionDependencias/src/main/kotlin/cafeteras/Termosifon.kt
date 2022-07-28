package koin.cafeteras

import java.util.*

data class Termosifon(private val calentador: Calentador) : Bomba {
    private val id = UUID.randomUUID()

    override fun bombear() {
        if (calentador.estaCaliente()) {
            println("=> => bombeando => =>")
        }
    }

    override fun toString(): String {
        return "Termosifon(id=$id, calentador=$calentador)"
    }
}

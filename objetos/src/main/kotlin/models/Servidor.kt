package models

import java.util.*

// Singleton
object Servidor {
    val uuid = UUID.randomUUID()
    var marca: String = "Pepe"

    override fun toString(): String {
        return "Servidor(uuid=$uuid, marca='$marca')"
    }
}
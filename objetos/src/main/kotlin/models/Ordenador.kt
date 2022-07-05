package models

import java.util.*

// POKO for the user model = Plain Old Kotlin Object
data class Ordenador(val uuid: UUID = UUID.randomUUID(), var marca: String, var precio: Double = 99.9)
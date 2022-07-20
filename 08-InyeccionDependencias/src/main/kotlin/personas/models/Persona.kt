package koin.personas.models

import java.util.*

data class Persona(
    val id: UUID = UUID.randomUUID(),
    val nombre: String,
    val apellido: String,
    val dni: String,
)


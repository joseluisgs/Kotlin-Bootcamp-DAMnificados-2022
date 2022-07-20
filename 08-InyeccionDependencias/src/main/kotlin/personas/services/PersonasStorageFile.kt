package koin.personas.services

import koin.personas.models.Persona
import java.util.*

class PersonasStorageFile : IPersonasStorage {
    private val id = UUID.randomUUID()

    override fun save(item: Persona): Persona {
        println("PersonasStorageFile.save() --> $item")
        return item
    }

    override fun toString() = "PersonaDataBaseStorage(id='$id')"
}

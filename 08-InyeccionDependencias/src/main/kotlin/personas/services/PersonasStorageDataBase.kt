package koin.personas.services

import koin.personas.models.Persona
import java.util.*

class PersonasStorageDataBase : IPersonasStorage {
    private val id = UUID.randomUUID()

    override fun save(item: Persona): Persona {
        println("PersonasStorageDataBase.save() --> $item")
        return item
    }

    override fun toString() = "PersonaDataBaseStorage(id='$id')"
}

package koin.personas.repositories

import koin.personas.models.Persona
import java.util.*

interface IPersonasRepository : CrudRepository<Persona, UUID>
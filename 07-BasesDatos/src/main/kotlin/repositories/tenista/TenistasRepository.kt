package repositories.tenista

import models.Tenista
import repositories.CrudRepository
import java.util.*

interface TenistasRepository : CrudRepository<Tenista, UUID>
package repositories.tenista

import model.Tenista
import repositories.CrudRepository
import java.util.*

interface TenistasRepository : CrudRepository<Tenista, UUID>
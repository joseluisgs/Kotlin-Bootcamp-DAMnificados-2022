package repositories.tenista

import model.Tenista
import repositories.CrudRepository
import java.util.*

interface TenistaRepository : CrudRepository<Tenista, UUID>
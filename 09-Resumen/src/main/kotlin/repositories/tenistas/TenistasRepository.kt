package repositories.tenistas

import models.Tenista
import repositories.base.CrudRepository
import java.util.*

/**
 * Repositorio de tenistas
 */
interface TenistasRepository : CrudRepository<Tenista, UUID>
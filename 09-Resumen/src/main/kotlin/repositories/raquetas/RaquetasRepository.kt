package repositories.raquetas

import models.Raqueta
import repositories.base.CrudRepository
import java.util.*

/**
 * Repositorio de raquetas
 */
interface RaquetasRepository : CrudRepository<Raqueta, UUID>
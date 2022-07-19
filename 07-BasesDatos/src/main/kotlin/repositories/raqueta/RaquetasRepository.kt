package repositories.raqueta

import model.Raqueta
import repositories.CrudRepository
import java.util.*

interface RaquetasRepository : CrudRepository<Raqueta, UUID> {
    //fun getTenistas(entity: Raqueta): List<Tenista>
}
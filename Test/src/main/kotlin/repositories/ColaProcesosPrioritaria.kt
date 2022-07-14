package repositories

import models.Proceso
import tda.Cola
import java.util.*

interface ColaProcesosPrioritaria : Cola<Proceso> {
    val size: Int
    fun getAll(): List<Proceso> // Ordenada por priridad
    fun getById(id: UUID): Proceso?
}
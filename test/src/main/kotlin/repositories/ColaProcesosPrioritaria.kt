package repositories

import models.Proceso
import tda.Cola

interface ColaProcesosPrioritaria : Cola<Proceso> {
    val size: Int
    fun getAll(): List<Proceso> // Ordenada por priridad
}
package repositories

import models.Proceso
import java.util.*

class ColaProcesosPrioritariaImp : ColaProcesosPrioritaria {
    val cola = mutableListOf<Proceso>()

    override val size: Int
        get() = cola.size

    override fun getAll(): List<Proceso> {
        return cola.sortedByDescending { it.prioridad }
    }

    override fun getById(id: UUID): Proceso? {
        return cola.find { it.id == id }
    }

    override fun push(item: Proceso): Boolean {
        val existe = cola.find { it.id == item.id }
        if (existe == null) {
            cola.add(item)
            return true
        }
        return false
    }

    override fun pop(): Proceso? {
        val p = cola.maxByOrNull { it.prioridad }
        cola.remove(p)
        return p
    }

    override fun isEmpty() = cola.isEmpty()
}
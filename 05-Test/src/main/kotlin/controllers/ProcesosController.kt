package controllers

import Exceptions.ProcesoException
import models.Proceso
import repositories.ColaProcesosPrioritaria
import java.util.*

class ProcesosController(private val cola: ColaProcesosPrioritaria) {

    fun push(proceso: Proceso) = cola.push(proceso)

    fun pop() = cola.pop() ?: throw ProcesoException("La cola está vacía")

    fun getAll(): List<Proceso> = cola.getAll()

    fun isVacia() = cola.isEmpty()

    fun size() = cola.size

    fun get(id: UUID): Proceso = cola.getById(id) ?: throw ProcesoException("No existe proceso con id $id")

}
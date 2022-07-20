package services.base

import java.io.File

/**
 * Sistema de almacenamiento básico
 */
interface Storage<T> {
    /**
     * Carga de un fichero de almacenamiento
     * @param file Fichero de almacenamiento
     * @returns Objeto de almacenamiento
     */
    fun loadFromFile(file: File): List<T>

    /**
     * Guardado de un fichero de almacenamiento
     * @param file Fichero de almacenamiento
     * @param data Objeto de almacenamiento
     */
    fun saveToFile(file: File, data: List<T>)
}